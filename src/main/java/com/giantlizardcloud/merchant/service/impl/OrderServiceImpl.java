package com.giantlizardcloud.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.dto.QueryOrderByConditionDto;
import com.giantlizardcloud.merchant.entity.*;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import com.giantlizardcloud.merchant.mapper.OrderMapper;
import com.giantlizardcloud.merchant.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.merchant.vo.InitOrderVo;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import com.giantlizardcloud.sys.entity.User;
import com.giantlizardcloud.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-27
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IOrderDetailsService detailsService;
    private final IInventoryService inventoryService;
    private final IClientService clientService;
    private final IShopService shopService;
    private final IUserService userService;
    private final RedisOperator operator;
    private final ICommodityService commodityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrderAndOrderDetails(AddOrderAndDetailDto dto) {
        Order order = new Order();
        LocalDate now = LocalDate.now();
        BeanUtils.copyProperties(dto, order);
        long orderId = new IdWorker().nextId();
        order.setOrderId(orderId);
        this.save(order);
        dto.getDetails().forEach((details -> {
            inventoryService.deductInventory(details.getShopId(), details.getCommodityId(), details.getOrderDetailNumber());
            details.setOrderId(orderId);
            detailsService.save(details);
            Commodity commodity = commodityService.getOne(new QueryWrapper<Commodity>().select("commodity_name").eq("commodity_id", details.getCommodityId()));
            operator.incrementScore(IndexKeyEnum.SALE.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                    ,commodity.getCommodityName(),details.getOrderDetailNumber());
        }));
        /* 判断是否结清,未结清则给客户增加欠款 */
        if (0 < dto.getOrderUnpaidAmount()) {
            clientService.update(null, new UpdateWrapper<Client>().setSql("client_consumption = client_consumption +"
                    + dto.getOrderUnpaidAmount()).eq("client_id", dto.getClientId()));
        }
        operator.hashIncrBy(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.SALE_COUNT.getMessage(),1);
        operator.hIncrByDouble(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.SALE_AMOUNT.getMessage(),dto.getOrderAmountAfterDiscount());
    }

    @Override
    public Page<OrderAndClientAndUserVO> getPage(Integer page, Integer size, Integer status) {
        Integer newPage = (page - 1) * size;
        List<OrderAndClientAndUserVO> list = this.baseMapper.getPage(newPage, size,status);
        Page<OrderAndClientAndUserVO> vos = new Page<>(page, size);
        Integer total = this.baseMapper.selectCount(new QueryWrapper<>());
        vos.setTotal(total);
        vos.setRecords(list);
        vos.setPages(page);
        vos.setSize(size);
        if ((total%size)==0){
            vos.setCurrent(total/size);
        }else {
            vos.setCurrent((total/size)+1);
        }
        return vos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidOrder(Long orderId) {
        Order order = this.baseMapper.selectOne(new QueryWrapper<Order>().eq("order_id",orderId));
        this.baseMapper.update(null,new UpdateWrapper<Order>().set("order_status",3).eq("order_id",orderId));
        List<OrderDetails> detailsList = detailsService.list(new QueryWrapper<OrderDetails>().eq("order_id", orderId));
        //回增库存
        detailsList.forEach(orderDetails -> inventoryService.increaseInventory(orderDetails.getShopId(),orderDetails.getCommodityId(),orderDetails.getOrderDetailNumber()));
        if (order.getOrderUnpaidAmount() != 0){
            clientService.update(null, new UpdateWrapper<Client>().setSql("client_consumption = client_consumption -"
                    + order.getOrderUnpaidAmount()).eq("client_id", order.getClientId()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnedOrder(AddOrderAndDetailDto dto) {
        Order returnOrder = new Order();
        LocalDate now = LocalDate.now();
        BeanUtils.copyProperties(dto, returnOrder);
        long orderId = new IdWorker().nextId();
        returnOrder.setOrderId(orderId);
        this.save(returnOrder);
        dto.getDetails().forEach((details -> {
            inventoryService.increaseInventory(details.getShopId(), details.getCommodityId(), details.getOrderDetailNumber());
            details.setOrderId(orderId);
            detailsService.save(details);
            Commodity commodity = commodityService.getOne(new QueryWrapper<Commodity>().select("commodity_name").eq("commodity_id", details.getCommodityId()));
            operator.incrementScore(IndexKeyEnum.SALE.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                    ,commodity.getCommodityName(),-details.getOrderDetailNumber());
        }));
        /* 判断是否结清,未结清则给客户增加欠款 */
        if (0 < dto.getOrderUnpaidAmount()) {
            clientService.update(null, new UpdateWrapper<Client>().setSql("client_consumption = client_consumption -"
                    + dto.getOrderUnpaidAmount()).eq("client_id", dto.getClientId()));
        }
        operator.hashIncrBy(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.SALE_COUNT.getMessage(),-1);
        operator.hIncrByDouble(IndexKeyEnum.STATISTICS.getMessage()+":"+now.getYear() + "-" + now.getMonthValue()
                ,IndexKeyEnum.SALE_AMOUNT.getMessage(),-dto.getOrderAmountAfterDiscount());
    }

    @Override
    public InitOrderVo initOrder() {
        return new InitOrderVo("xsd-"+new IdWorker().nextId()
                ,clientService.list(new QueryWrapper<Client>().eq("client_status", 1))
                ,shopService.list(new QueryWrapper<Shop>().le("shop_status",2))
                ,userService.list(new QueryWrapper<User>().select("user_id","username").eq("status","NORMAL"))
        );
    }

    @Override
    public Page<OrderAndClientAndUserVO> getPageByCondition(QueryOrderByConditionDto dto) {
        Integer newPage = (dto.getPage() - 1) * dto.getSize();
        dto.setPage(newPage);
        List<OrderAndClientAndUserVO> list = this.baseMapper.getPageByCondition(dto);
        Page<OrderAndClientAndUserVO> vos = new Page<>(dto.getPage(),dto.getSize());
        Integer total = this.baseMapper.selectConditionCount(dto);
        vos.setTotal(total);
        vos.setRecords(list);
        if ((total%dto.getSize())==0){
            vos.setCurrent(total/dto.getSize());
        }else {
            vos.setCurrent((total/dto.getSize())+1);
        }
        return vos;
    }

    public OrderServiceImpl(IOrderDetailsService detailsService, IInventoryService inventoryService,
                            IClientService clientService, IShopService shopService, IUserService userService, RedisOperator operator,ICommodityService commodityService) {
        this.detailsService = detailsService;
        this.inventoryService = inventoryService;
        this.clientService = clientService;
        this.shopService =shopService;
        this.userService = userService;
        this.operator = operator;
        this.commodityService = commodityService;
    }
}
