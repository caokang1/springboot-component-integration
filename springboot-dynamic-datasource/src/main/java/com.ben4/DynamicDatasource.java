package com.ben4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by caokang on 19/12/31.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class DynamicDatasource {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasource.class, args);
    }
}
