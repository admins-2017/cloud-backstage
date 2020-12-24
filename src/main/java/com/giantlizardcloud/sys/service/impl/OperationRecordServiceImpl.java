package com.giantlizardcloud.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.dto.RecordDto;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.giantlizardcloud.sys.mapper.OperationRecordMapper;
import com.giantlizardcloud.sys.service.IOperationRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-03
 */
@Service
@Slf4j
public class OperationRecordServiceImpl extends ServiceImpl<OperationRecordMapper, OperationRecord> implements IOperationRecordService {

    @Override
    public IPage<OperationRecord> getRecordByCondition(RecordDto dto) {
        log.info(dto.toString());
        Page<OperationRecord> recordPage = page(new Page<>(dto.getPage(), dto.getSize())
                , new QueryWrapper<OperationRecord>()
                .select("request_type","request_url","request_user","request_time","request_ip","description","requestMethod")
                .eq(!dto.getRequestUser().equals(""),"request_user",dto.getRequestUser())
                .eq(!dto.getRequestType().equals(""),"request_type",dto.getRequestType())
                .between(!dto.getStartTime().equals("")&&!dto.getEndTime().equals("")
                ,"request_time",dto.getStartTime(),dto.getEndTime()).orderByDesc("request_time")
        );
        return recordPage;
    }
}
