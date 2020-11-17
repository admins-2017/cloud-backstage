package com.giantlizardcloud.merchant.controller;


import com.giantlizardcloud.config.json.JSONResult;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseOrderController {

    @PostMapping
    public JSONResult addPurchaseOrder(){
        return JSONResult.ok();
    }

    @DeleteMapping("/orderId")
    public JSONResult invalidOrder(){
        return JSONResult.ok();
    }

    @GetMapping("/{page}/{size}/{status}")
    public JSONResult getOrderByPage(@PathVariable Integer page , @PathVariable Integer size , @PathVariable Integer status){
        return JSONResult.ok();
    }

    @GetMapping
    public JSONResult getOrderByCondition(){
        return JSONResult.ok();
    }

    @GetMapping("/init")
    public JSONResult initOrder(){
        return JSONResult.ok();
    }

}
