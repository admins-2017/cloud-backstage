package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.service.IOrderService;
import com.giantlizardcloud.merchant.vo.InitOrderVo;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
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
        InitOrderVo vo = orderService.initOrder();
        return JSONResult.ok(vo);
    }

    @GetMapping("/{status}/{page}/{size}")
    public JSONResult getOrderWithDetailByPage(@PathVariable Integer status,@PathVariable Integer page,@PathVariable Integer size){
        Page<OrderAndClientAndUserVO> vos = orderService.getPage(page,size,status);
        return JSONResult.ok(vos);
    }

    @PostMapping("/returned")
    public JSONResult orderReturned(@RequestBody AddOrderAndDetailDto dto){
        orderService.returnedOrder(dto);
        return JSONResult.ok();
    }

    @DeleteMapping("/{orderId}")
    public JSONResult invalidOrder(@PathVariable Long orderId){
        orderService.invalidOrder(orderId);
        return JSONResult.ok("作废订单成功");
    }

}
