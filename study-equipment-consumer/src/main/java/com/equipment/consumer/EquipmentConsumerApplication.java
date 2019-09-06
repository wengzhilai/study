package com.equipment.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class EquipmentConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EquipmentConsumerApplication.class, args);
    }
}
