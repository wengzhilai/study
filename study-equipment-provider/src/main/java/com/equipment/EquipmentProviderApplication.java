package com.equipment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages={"com.equipment.mapper"})//扫描Mapper
@ComponentScan(basePackages = "com.equipment")
public class EquipmentProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EquipmentProviderApplication.class, args);
    }

}