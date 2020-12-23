package com.giantlizardcloud.repayment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.entity.Repayment;
import com.giantlizardcloud.merchant.service.IRepaymentService;
import com.giantlizardcloud.merchant.service.ISettleService;
import com.giantlizardcloud.merchant.service.ISupplierService;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class RepaymentTest {

//    @Autowired
//    private IRepaymentService service;
//    @Autowired
//    private ISupplierService supplierService;
//    @Autowired
//    private ISettleService settleService;
//
//    @Test
//    public void testGetRepayment(){
//        QueryRepaymentDto dto = new QueryRepaymentDto();
//        dto.setPage(0);
//        dto.setSize(10);
//        dto.setRepaymentStatus(2);
////        dto.setSupplierId(1L);
//        List<RepaymentWithAnnexVo> repayment =
//                service.getRepayment(dto);
//        repayment.forEach(System.out::println);
//    }
//
//    @Test
//    public void testAddSupplierArrears(){
//        supplierService.addSupplierArrears(5153136104785680L,100.0);
//    }
//
//    @Test
//    public void testMinusSupplierArrears(){
//        supplierService.minusSupplierArrears(5153136104785680L,100.0);
//    }
//
//    @Test
//    public void testSupplierArrears(){
//        Repayment repayment = service.getOne(new QueryWrapper<Repayment>()
//                .select("supplier_id", "repayment_sum").eq("repayment_id", 6));
//        supplierService.addSupplierArrears(repayment.getSupplierId(),repayment.getRepaymentSum());
//    }
//
//    @Test
//    public void testAddRepayment(){
//        AddRepaymentDto addRepaymentDto = new AddRepaymentDto();
//        addRepaymentDto.setSupplierId(3L);
//        addRepaymentDto.setRepaymentSum(3000.0);
//        addRepaymentDto.setRepaymentRemark("测试2");
//        addRepaymentDto.setRepaymentNumber("hkd-202012161930003");
//        addRepaymentDto.setRepaymentMethod(1);
//        addRepaymentDto.setRepaymentDate(LocalDateTime.now());
//        addRepaymentDto.setRepaymentAccountNumber("102553221477855");
//        List<String> list = new ArrayList<>();
//        list.add("localhost:8080/c.jpg");
//        list.add("localhost:8080/d.jpg");
//        addRepaymentDto.setChildren(list);
//        service.addRepayment(addRepaymentDto);
//
//    }
//
//    @Test
//    public void testUpdate(){
//        service.invalidRepayment(1L);
//    }
//
//    @Test
//    public void testGetSettle(){
//        settleService.list();
//    }
}
