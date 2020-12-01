package com.giantlizardcloud.vo;

import lombok.Data;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
@Data
public class IndexDataVo {

    private Set<ZSetOperations.TypedTuple<String>> commodityList;

    private Map<Object, Object> number;

}
