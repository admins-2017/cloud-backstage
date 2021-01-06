package com.giantlizardcloud.dto.job;

import lombok.Data;

/**
 * @author kang
 */
@Data
public class QueryJobDto {

    private Integer page;

    private Integer size;

    private String likeName;

    private Integer status;

}
