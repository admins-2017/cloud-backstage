package com.giantlizardcloud.config.aspect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpJsonEntity implements Serializable {

    private String Country;

    private String Province;

    private String City;

    private String Isp;
}
