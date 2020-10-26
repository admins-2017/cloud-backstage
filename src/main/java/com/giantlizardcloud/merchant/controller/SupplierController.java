package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.poi.util.FileUtils;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.dto.InsertSupplierDto;
import com.giantlizardcloud.dto.UpdateSupplierDto;
import com.giantlizardcloud.merchant.dto.QuerySupplierDto;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.merchant.service.ISupplierService;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import com.giantlizardcloud.vo.SupplierVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/supplier")
@Api(value = "供应商管理", tags = "供应商对应操作")
@Slf4j
public class SupplierController {

    private final ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    @ApiOperation(value = "获取所有供应商")
    public JSONResult getSupplier() {
        List<SupplierVo> supplierList = supplierService.getAllSupplierBySupplierStatus();
        return JSONResult.ok(supplierList);
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation(value = "获取所有状态供应商（分页）")
    public JSONResult getAllSupplierByPage(@PathVariable Integer page, @PathVariable Integer size) {
        return JSONResult.ok(supplierService.page(new Page<>(page, size)));
    }


    @GetMapping("/query")
    @ApiOperation(value = "根据供应商名模糊查询获取供应商", notes = "供应商名")
    public JSONResult getSupplierLikeName(QuerySupplierDto dto) {
        IPage<Supplier> suppliers =
                supplierService.page(new Page<>(dto.getPage(), dto.getSize())
                        , new QueryWrapper<Supplier>().like(!"".equals(dto.getSupplierName())&&null!=dto.getSupplierName(), "supplier_name", dto.getSupplierName())
                                .like(!"".equals(dto.getSupplierTelephone())&&null!=dto.getSupplierTelephone(),"supplier_telephone",dto.getSupplierTelephone())
                                .like(!"".equals(dto.getSupplierEmail())&&null!=dto.getSupplierEmail(),"supplier_email",dto.getSupplierEmail())
                                .eq(null!=dto.getSupplierStatus(),"supplier_status",dto.getSupplierStatus()));
        return JSONResult.ok(suppliers);
    }

    @PostMapping
    public JSONResult insertSupplier(InsertSupplierDto dto) {
        System.out.println(dto.toString());
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(dto, supplier);
        supplier.setSupplierId(new IdWorker().nextId());
        supplierService.save(supplier);
        return JSONResult.ok("新增供应商成功");
    }



    @PutMapping("/status/{status}/{supplierId}")
    @ApiOperation(value = "启停供应商")
    public JSONResult updateStatusSupplierByStop(@PathVariable Integer status, @PathVariable Long supplierId) {
        System.out.println(supplierId);
        if (status == 1) {
            supplierService.update(new UpdateWrapper<Supplier>().set("supplier_status", 1).eq("supplier_id", supplierId));
            return JSONResult.ok("恢复供应商成功");
        } else {
            supplierService.update(new UpdateWrapper<Supplier>().set("supplier_status", 0).eq("supplier_id", supplierId));
            return JSONResult.ok("停用供应商成功");
        }
    }

    @PutMapping
    public JSONResult updateSupplier(UpdateSupplierDto dto) {
        log.info(dto.toString());
        supplierService.update(new UpdateWrapper<Supplier>()
                .set(dto.getSupplierName() != null && !"".equals(dto.getSupplierName()), "supplier_name", dto.getSupplierName())
                .set(dto.getSupplierCapital() != null, "supplier_capital", dto.getSupplierCapital())
                .set(dto.getSupplierAddress() != null && !"".equals(dto.getSupplierAddress()), "supplier_address", dto.getSupplierAddress())
                .set(dto.getSupplierPerson() != null && !"".equals(dto.getSupplierPerson()), "supplier_person", dto.getSupplierPerson())
                .set(dto.getSupplierTelephone() != null && !"".equals(dto.getSupplierTelephone()), "supplier_telephone", dto.getSupplierTelephone())
                .set(dto.getSupplierEmail() != null && !"".equals(dto.getSupplierEmail()), "supplier_email", dto.getSupplierEmail())
                .set(dto.getSupplierBusiness() != null && !"".equals(dto.getSupplierBusiness()), "supplier_business", dto.getSupplierBusiness())
                .set(dto.getSupplierCooperated() != null, "supplier_cooperated", dto.getSupplierCooperated())
                .eq("supplier_id", dto.getSupplierId()));
        return JSONResult.ok("修改供应商成功");
    }

    @GetMapping("/export")
    public void exportSupplier(HttpServletResponse response,QuerySupplierDto dto){
        List<Supplier> vos = supplierService.list(new QueryWrapper<Supplier>().like(!"".equals(dto.getSupplierName()) && null != dto.getSupplierName(), "supplier_name", dto.getSupplierName())
                .like(!"".equals(dto.getSupplierTelephone()) && null != dto.getSupplierTelephone(), "supplier_telephone", dto.getSupplierTelephone())
                .like(!"".equals(dto.getSupplierEmail()) && null != dto.getSupplierEmail(), "supplier_email", dto.getSupplierEmail())
                .eq(null != dto.getSupplierStatus(), "supplier_status", dto.getSupplierStatus()));
        if (vos.size()>0){
            FileUtils.exportExcel(vos, "供应商详情" , "", Supplier.class, "供应商.xls", response);
        }else{
            FileUtils.exportExcel(null, "供应商详情" , "", Supplier.class, "供应商.xls", response);
        }
    }

}
