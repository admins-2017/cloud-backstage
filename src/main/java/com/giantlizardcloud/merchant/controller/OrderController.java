package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.dto.QueryOrderByConditionDto;
import com.giantlizardcloud.merchant.service.IOrderService;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kang
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public JSONResult addOrder(@RequestBody AddOrderAndDetailDto dto){
        orderService.addOrderAndOrderDetails(dto);
        return JSONResult.ok("新增订单成功");
    }

    @GetMapping("/init")
    public JSONResult initOrder(){
        return JSONResult.ok(orderService.initOrder());
    }

    @GetMapping("/{status}/{page}/{size}")
    public JSONResult getOrderWithDetailByPage(@PathVariable Integer status,@PathVariable Integer page,@PathVariable Integer size){
        return JSONResult.ok(orderService.getPage(page,size,status));
    }

    @PostMapping("/returned")
    public JSONResult orderReturned(@RequestBody AddOrderAndDetailDto dto){
        orderService.returnedOrder(dto);
        return JSONResult.ok("销售退货成功");
    }

    @DeleteMapping("/{orderId}")
    public JSONResult invalidOrder(@PathVariable Long orderId){
        orderService.invalidOrder(orderId);
        return JSONResult.ok("作废订单成功");
    }

    @GetMapping
    public JSONResult getOrderByCondition(QueryOrderByConditionDto dto){
        Page<OrderAndClientAndUserVO> vo = orderService.getPageByCondition(dto);
        return JSONResult.ok();
    }

}
