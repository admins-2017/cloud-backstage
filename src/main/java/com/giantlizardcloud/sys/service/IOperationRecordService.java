package com.giantlizardcloud.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.dto.RecordDto;
import com.giantlizardcloud.sys.entity.OperationRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-03
 */
public interface IOperationRecordService extends IService<OperationRecord> {

    /**
     * 根据查询条件查询操作记录
     * @param dto 查询条件
     * @return 操作记录列表
     */
    IPage<OperationRecord> getRecordByCondition(RecordDto dto);
}
