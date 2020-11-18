package com.giantlizardcloud.merchant.controller;


import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddPurchaseOrderDto;
import com.giantlizardcloud.merchant.enums.PurchaseStatusEnum;
import com.giantlizardcloud.merchant.service.IPurchaseOrderService;
import com.giantlizardcloud.merchant.vo.InitPurchaseOrderVo;
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

    private final IPurchaseOrderService purchaseOrderService;


    @PostMapping
    public JSONResult addPurchaseOrder(@RequestBody AddPurchaseOrderDto dto){
        if (dto.getPurchaseStatus().equals(PurchaseStatusEnum.SAVE.getCode())){
            purchaseOrderService.savePurchaseOrder(dto);
            return JSONResult.ok("已保存采购单");
        }else {
            purchaseOrderService.returnPurchaseOrder(dto);
            return JSONResult.ok("已保存采购退货单");
        }
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
        return JSONResult.ok(purchaseOrderService.init());
    }

    public PurchaseOrderController(IPurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }
}
