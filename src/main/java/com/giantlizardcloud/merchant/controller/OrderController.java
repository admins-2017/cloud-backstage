package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddOrderAndDetailDto;
import com.giantlizardcloud.merchant.service.IOrderService;
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
        return JSONResult.ok();
    }

    @GetMapping("/{status}/{page}/{size}")
    public JSONResult getOrderWithDetailByPage(@PathVariable Integer status,@PathVariable Integer page,@PathVariable Integer size){
        Page<OrderAndClientAndUserVO> vos = orderService.getPage(page,size,status);
        return JSONResult.ok(vos);
    }

    @DeleteMapping
    public JSONResult invalidOrder(){
        return JSONResult.ok();
    }
}
