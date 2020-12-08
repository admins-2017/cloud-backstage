package com.giantlizardcloud.purchaseTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.dto.QueryPurchaseOrderDto;
import com.giantlizardcloud.merchant.entity.Inventory;
import com.giantlizardcloud.merchant.entity.PurchaseOrder;
import com.giantlizardcloud.merchant.entity.PurchaseOrderDetails;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.enums.PurchaseStatusEnum;
import com.giantlizardcloud.merchant.mapper.PurchaseOrderDetailsMapper;
import com.giantlizardcloud.merchant.mapper.PurchaseOrderMapper;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.service.IPurchaseOrderDetailsService;
import com.giantlizardcloud.merchant.service.IPurchaseOrderService;
import com.giantlizardcloud.merchant.vo.InitPurchaseOrderVo;
import com.giantlizardcloud.merchant.vo.PurchaseOrderDetailsVo;
import com.giantlizardcloud.merchant.vo.PurchasePageVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class PurchaseTest {

//    @Autowired
//    private IPurchaseOrderService orderService;
//    @Autowired
//    private IInventoryService inventoryService;
//    @Autowired
//    private IPurchaseOrderDetailsService detailsService;
//    @Autowired
//    private PurchaseOrderMapper orderMapper;
//    @Autowired
//    private PurchaseOrderDetailsMapper detailsMapper;
//
//    private static void accept(Shop s) {
//        log.info(s.toString());
//    }
//
//    @Test
//    public void testPurchaseInit(){
//        InitPurchaseOrderVo init = orderService.init();
//        log.info(init.getOrderNumber());
//        init.getShops().forEach(PurchaseTest::accept);
//        init.getSuppliers().forEach(supplier -> {
//            log.info(supplier.toString());
//        });
//        init.getUsers().forEach( user -> {
//            log.info(user.toString());
//        });
//    }
//
//    @Test
//    public void testEnum(){
//        log.info(String.valueOf(PurchaseStatusEnum.SAVE.getCode()));
//    }
//
//    @Test
//    public void testSavePurchaseOrder(){
//        List<PurchaseOrderDetails> details = new ArrayList<>();
//        details.add(new PurchaseOrderDetails(5,249526048729019l,10, BigDecimal.valueOf(50)));
//        details.add(new PurchaseOrderDetails(6,249526048729019l,7, BigDecimal.valueOf(50)));
//
//        AddPurchaseOrderDto addPurchaseOrderDto = new AddPurchaseOrderDto("cgd-151200001563", LocalDateTime.now(),5153136104785680l,100.0,0.9,90.00,0.00,90.0
//                ,1,"","测试四次",1,5080701217932992l,details);
//        orderService.savePurchaseOrder(addPurchaseOrderDto);
//    }
//
//    @Test
//    public void testInventory(){
//        int count = inventoryService.count(new QueryWrapper<Inventory>().eq("commodity_id", 5).eq("shop_id", 249526048729019l));
//        log.info(String.valueOf(count));
//    }
//
//    @Test
//    public void testGetDetails(){
//        detailsService.list(new QueryWrapper<PurchaseOrderDetails>().eq("purchase_id",5191388899247904l)).forEach(details -> {
//            System.out.println(details.toString());
//        });
//    }
//
//    @Test
//    public void testInvalidOrder(){
//        Long orderId = 5191388899247904L;
//        orderService.invalidOrder(orderId);
//    }
//
//    @Test
//    public void testPurchaseByPage(){
//        Page<PurchasePageVo> page = orderService.getOrderByPage(1, 10, 1);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getTotal());
//        System.out.println(page.getPages());
//        System.out.println(page.getSize());
//        page.getRecords().forEach(System.out::println);
//    }
//
//    @Test
//    public void testPurchaseDetails(){
//        detailsMapper.getDetailsByOrderId(5191388899247904l).forEach(System.out::println);
//
//    }
//
//    @Test
//    public void testCount(){
//        Integer count = orderMapper.selectCount(new QueryWrapper<PurchaseOrder>().eq("purchase_status",1));
//        System.out.println(count);
//    }
//
//    @Test
//    public void testCondition(){
//        QueryPurchaseOrderDto dto = new QueryPurchaseOrderDto();
//        dto.setPage(1);
//        dto.setSize(10);
//        dto.setStartTime("2020-11-17");
//        dto.setEndTime("2020-11-20");
//        Page<PurchasePageVo> page = orderService.getOrderByCondition(dto);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getTotal());
//        System.out.println(page.getPages());
//        System.out.println(page.getSize());
//        page.getRecords().forEach(System.out::println);
//    }
//
//    @Test
//    public void testMath(){
//        int a = (0-1)*10;
//        System.out.println(a);
//    }

}
