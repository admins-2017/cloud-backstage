package com.giantlizardcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.giantlizardcloud.*.mapper")
@EnableAsync
public class CloudBackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBackstageApplication.class, args);
    }

}
