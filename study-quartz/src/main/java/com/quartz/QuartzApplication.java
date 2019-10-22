package com.quartz;

import com.quartz.jobs.HelloWorldJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@SpringBootApplication
@EnableSwagger2
public class QuartzApplication {
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(QuartzApplication.class, args);
    }
}

