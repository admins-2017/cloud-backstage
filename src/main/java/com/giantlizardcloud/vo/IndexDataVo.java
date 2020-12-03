package com.giantlizardcloud.vo;

import com.giantlizardcloud.merchant.entity.Statistics;
import lombok.Data;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
@Data
public class IndexDataVo {

    private Set<ZSetOperations.TypedTuple<String>> commodityList;

    private Map<Object, Object> number;

    private List<Statistics> statistics;

}
