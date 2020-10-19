package com.giantlizardcloud.merchant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.giantlizardcloud.merchant.entity.Supplier;
import com.giantlizardcloud.vo.SupplierVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
public interface SupplierMapper extends BaseMapper<Supplier> {


      /**
       * 根据状态获取
       * @return 供应商
       */
      @Select("select sup.supplier_id,sup.supplier_name,sup.supplier_capital,\n" +
              "sup.supplier_address,sup.supplier_person,sup.supplier_telephone,\n" +
              "sup.supplier_email,sup.supplier_business,sup.supplier_cooperated,\n" +
              "sup.supplier_status,sup.shop_id,\n" +
              "shp.shop_name from merchant_supplier sup\n" +
              "left join merchant_shop shp\n" +
              "on sup.shop_id = shp.shop_id\n" +
              "where sup.supplier_status = 1")
      List<SupplierVo> getAllSupplierBySupplierStatus();

      /**
       * 模糊查询供应商
       * @param supplierName 供应商名
       * @param page 页码
       * @param size 条数
       * @return 供应商集合
       */
      List<Supplier> selectAllSupplierWithUser(@Param("supplierName") String supplierName, @Param("page") Integer page, @Param("size") Integer size);

}
