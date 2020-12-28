package com.giantlizardcloud.settle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.merchant.dto.AddSettleDto;
import com.giantlizardcloud.merchant.dto.QuerySettleDto;
import com.giantlizardcloud.merchant.service.ISettleService;
import com.giantlizardcloud.merchant.vo.SettleWithAnnexVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class SettleTest {
//
//    @Autowired
//    private ISettleService settleService;
//
//    @Test
//    public void insertSettle(){
//        AddSettleDto dto = new AddSettleDto();
//        dto.setClientId(5154422908323664L);
//        dto.setSettleAccountNumber("59563355231");
//        dto.setSettleDate(LocalDateTime.now());
//        dto.setSettleMethod(1);
//        dto.setSettleNumber("jqd-2020122219290007");
//        dto.setSettleRemark("测试结清一下7");
//        dto.setSettleSum(100.0);
//        List<String> urls = new ArrayList<>();
//        urls.add("localhost:8080/jqd-2020122219290001/a.jpg");
//        urls.add("localhost:8080/jqd-2020122219290001/b.jpg");
//        dto.setUrls(urls);
//        settleService.addSettle(dto);
//    }
//
//    @Test
//    public void invalidSettle(){
//        settleService.invalidSettle(5239694901929184L);
//    }
//
//    @Test
//    public void getSettleByCondition(){
//        QuerySettleDto dto = new QuerySettleDto();
//        dto.setPage(1);
//        dto.setSize(10);
//        dto.setSettleNumber("jqd-2020122219290006");
//        dto.setClientId(5154422908323664L);
//        dto.setSettleMethod(1);
//        dto.setSettleStartDate("2020-12-12 00:00:00");
//        dto.setSettleEndDate("2020-12-20 23:59:54");
//        dto.setSettleStatus(1);
//        IPage<SettleWithAnnexVo> list = settleService.getSettleByCondition(dto);
//        System.out.println(list.getTotal());
//        list.getRecords().forEach(System.out::println);
//    }

}
