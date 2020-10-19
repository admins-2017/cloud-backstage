package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.dto.InsertSupplierDto;
import com.giantlizardcloud.dto.UpdateSupplierDto;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.merchant.service.ISupplierService;
import com.giantlizardcloud.vo.SupplierVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/merchant/supplier")
@Api(value = "供应商管理",tags = "供应商对应操作")
@Slf4j
public class SupplierController {

    private final ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
//    @SysOperationLog(description = "获取所有供应商")
    @ApiOperation(value = "获取所有供应商")
    public JSONResult getSupplier(){
        List<SupplierVo> supplierList =
                supplierService.getAllSupplierBySupplierStatus();
        return JSONResult.ok(supplierList);
         }


    @GetMapping("/{page}/{size}")
//    @SysOperationLog(description = "获取所有供应商")
    @ApiOperation(value = "获取所有供应商")
     public JSONResult getAllSupplierByPage(@PathVariable Integer page, @PathVariable Integer size){
        IPage<Supplier> suppliers = supplierService.selectAllSupplierWithUser(null, page, size);
        return JSONResult.ok(suppliers);
    }


    @GetMapping("/{name}")
//    @SysOperationLog(description = "根据供应商名模糊查询获取供应商")
    @ApiOperation(value = "根据供应商名模糊查询获取供应商",notes = "供应商名")
    public JSONResult getSupplierLikeName(@PathVariable String name){
        List<Supplier> list = supplierService.list(new QueryWrapper<Supplier>().eq("supplier_status",1).likeRight("supplier_name", name));
        return JSONResult.ok(list);
    }

    @GetMapping("/{page}/{size}/{supplierName}")
//    @SysOperationLog(description = "根据查询条件获取所有供应商")
    @ApiOperation(value = "根据查询条件获取所有供应商")
    public JSONResult getSupplierByLikeName(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String supplierName){
        IPage<Supplier> suppliers= supplierService.selectAllSupplierWithUser(supplierName, page, size);
        return  JSONResult.ok(suppliers);
    }

    @PostMapping
    public JSONResult insertSupplier(InsertSupplierDto dto){
        log.info(dto.toString());
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(dto,supplier);
        supplier.setSupplierId(new IdWorker().nextId());
        supplierService.save(supplier);
        return  JSONResult.ok("新增供应商成功");
    }


    @PutMapping("/stop/{supplierId}")
    @ApiOperation(value="停用(删除)供应商")
    public JSONResult updateStatusSupplierByStop(@PathVariable Long supplierId){
        log.info(supplierId.toString());
        supplierService.update(new UpdateWrapper<Supplier>().set("supplier_status",0).eq("supplier_id",supplierId));
        return JSONResult.ok("停用(删除)供应商成功");
    }

    @PutMapping
    public JSONResult updateSupplier(UpdateSupplierDto dto){
        log.info(dto.toString());
        supplierService.update(new UpdateWrapper<Supplier>()
                .set(dto.getSupplierName()!=null&&!dto.getSupplierName().equals(""),"supplier_name",dto.getSupplierName())
                .set(dto.getSupplierCapital()!=null&&!dto.getSupplierCapital().equals(""),"supplier_capital",dto.getSupplierCapital())
                .set(dto.getSupplierAddress()!=null&&!dto.getSupplierAddress().equals(""),"supplier_address",dto.getSupplierAddress())
                .set(dto.getSupplierPerson()!=null&&!dto.getSupplierPerson().equals(""),"supplier_person",dto.getSupplierPerson())
                .set(dto.getSupplierTelephone()!=null&&!dto.getSupplierTelephone().equals(""),"supplier_telephone",dto.getSupplierTelephone())
                .set(dto.getSupplierEmail()!=null&&!dto.getSupplierEmail().equals(""),"supplier_email",dto.getSupplierEmail())
                .set(dto.getSupplierBusiness()!=null&&!dto.getSupplierBusiness().equals(""),"supplier_business",dto.getSupplierBusiness())
                .set(dto.getSupplierCooperated()!=null&&dto.getSupplierCooperated().equals(""),"supplier_ cooperated",dto.getSupplierCooperated())
                .set(dto.getSupplierStatus()!=null&&dto.getSupplierStatus().equals(""),"supplier_status",dto.getSupplierStatus())
                .eq("supplier_id",dto.getSupplierId()));
        return JSONResult.ok("修改供应商成功");
    }


}
