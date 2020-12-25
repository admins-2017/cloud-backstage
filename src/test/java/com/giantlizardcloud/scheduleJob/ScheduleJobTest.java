package com.giantlizardcloud.scheduleJob;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.dto.job.QueryJobDto;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.sys.service.IScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ScheduleJobTest {

    @Autowired
    private IScheduleJobService jobService;

    @Test
    public void test(){
        QueryJobDto dto = new QueryJobDto();
        dto.setPage(1);
        dto.setSize(10);
        IPage<ScheduleJobWithDetail> job = jobService.getJobByCondition(dto);
        log.info(String.valueOf(job.getTotal()));
        log.info(String.valueOf(job.getCurrent()));
        job.getRecords().forEach(System.out::println);
    }
}
