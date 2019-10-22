package com.quartz.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("任务已经启动..."+event.getSource());
    }

    @Bean
    public SchedulerFactory schedulerFactory() throws IOException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return  schedulerFactory;
    }


    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException, IOException {
        return schedulerFactory().getScheduler();
    }


    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
}
