package com.quartz.config;

import com.quartz.jobs.LoadTaskJob;
import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
@EnableScheduling
public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    Scheduler scheduler ;

    @Scheduled(cron = "1/5 * * * * ?")
    public void run() throws SchedulerException {

        System.out.println("1");
        JobDetail jobDetail= scheduler.getJobDetail(new JobKey("jobLoadTask", "jobGroup"));
        if(jobDetail==null) {
            //创建JobDetail实例，并与HelloWordlJob类绑定
            JobDetail jobDetailTask = JobBuilder.newJob(LoadTaskJob.class).withIdentity("jobLoadTask", "jobGroup").build();

            //创建触发器Trigger实例(立即执行，每隔1S执行一次)

            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("triggerJob", "triggerJobGroup")
                    .startNow()
                    .withSchedule(cronSchedule("1/10 * * * * ?"))
                    .build();
            //开始执行
            scheduler.scheduleJob(jobDetailTask, trigger1);
        }
        scheduler.start();
    }

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
