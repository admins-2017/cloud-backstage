 package com.giantlizardcloud.merchant.service.impl;


 import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
 import com.baomidou.mybatisplus.core.metadata.IPage;
 import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
 import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
 import com.giantlizardcloud.merchant.entity.Supplier;
 import com.giantlizardcloud.merchant.mapper.SupplierMapper;
 import com.giantlizardcloud.merchant.service.ISupplierService;
 import com.giantlizardcloud.vo.SupplierVo;
 import org.springframework.stereotype.Service;

 import java.util.List;

 /**
  * <p>
  *  服务实现类
  * </p>
  *
  * @author jobob
  * @since 2020-09-14
  */
 @Service
 public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {


     @Override
     public List<SupplierVo> getAllSupplierBySupplierStatus() {
         return this.baseMapper.getAllSupplierBySupplierStatus();
     }

     @Override
     public IPage<Supplier> selectAllSupplierWithUser(String supplierName, Integer page, Integer size) {
         page = (page - 1) * size;
         List<Supplier> suppliers = this.baseMapper.selectAllSupplierWithUser(supplierName,page,size);
         IPage<Supplier> supplierIPage = new Page<>();
         supplierIPage.setRecords(suppliers);
         if(null == supplierName){
             supplierIPage.setTotal(this.baseMapper.selectCount(new QueryWrapper<>()));
         }else {
             supplierIPage.setTotal(this.baseMapper.selectCount(new QueryWrapper<Supplier>().like("supplier_name",supplierName)));
         }
         return supplierIPage;
     }
 }
