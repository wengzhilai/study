package com.quartz;

import com.quartz.jobs.HelloWorldJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.quartz","com.dependencies.mybatis.service"})
@MapperScan(basePackages={"com.dependencies.mybatis.mapper"})//扫描Mapper
@EnableTransactionManagement
public class QuartzApplication {
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(QuartzApplication.class, args);
    }
}

