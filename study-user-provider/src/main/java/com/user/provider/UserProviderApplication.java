package com.user.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ComponentScan(basePackages = {"com.user.provider","com.dependencies.mybatis.service"})
@EnableSwagger2
@MapperScan(basePackages={"com.dependencies.mybatis.mapper"})//扫描Mapper
@EnableTransactionManagement
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }
}