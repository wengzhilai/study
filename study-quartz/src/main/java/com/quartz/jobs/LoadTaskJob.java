package com.quartz.jobs;

import com.quartz.server.ScriptService;
import com.wzl.commons.model.entity.FaScriptEntity;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Controller
public class LoadTaskJob implements Job {
    @Autowired
    ScriptService scriptService;

    @Autowired
    Scheduler scheduler ;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());
        System.out.println( strTime + ":Hello World！LoadTaskJob");
        List<FaScriptEntity> allTask=scriptService.getNormalScript();
        for (FaScriptEntity item : allTask) {
            GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.groupEquals("ScriptGroup");
            Set<TriggerKey> triggerList = null;
            try {
                triggerList = scheduler.getTriggerKeys(matcherTrigger);

                // 否存在item的触发器任务
                Optional<TriggerKey> triggerKey = triggerList.stream().filter(x -> x.getName().equals("triggerScript" + item.id)).findFirst();

                //表示任务存在
                if (triggerKey.get() != null) {
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey.get());
                    JobDetail job = scheduler.getJobDetail(trigger.getJobKey());
                if (!trigger.getCronExpression().equals(item.runWhen))
                {
                    // logger.InfoFormat("脚本服务 修改触发器【{0}】的时间表达式【{1}】为【{2}】", trigger.Key.Name, trigger.CronExpressionString, t.RUN_WHEN);
                    trigger.getTriggerBuilder().withSchedule(cronSchedule(item.runWhen));
                    scheduler.deleteJob(trigger.getJobKey());
                    scheduler.scheduleJob(job, trigger);
                }
                } else {
//
//                    JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class).withIdentity("jobHello", "jobGroup1").build();
//
//                    //创建触发器Trigger实例(立即执行，每隔1S执行一次)
//                    Trigger trigger = TriggerBuilder.newTrigger()
//                            .withIdentity("trigger1", "triggerGroup1")
//                            .startNow()
//                            .withSchedule(cronSchedule("1/5 * * * * ?"))
//                            .build();
//
//                    //开始执行
//                    scheduler.scheduleJob(jobDetail, trigger);
//                //3、创建一个触发器
//                    TriggerBuilder trigger = TriggerBuilder.create()
//                        .WithSimpleSchedule(x => x.WithIntervalInSeconds(2).RepeatForever())//每两秒执行一次
//                                    .WithCronSchedule(item.RUN_WHEN)
//                    .UsingJobData("scriptId", item.ID)  //通过在Trigger中添加参数值
//                    .WithIdentity("triggerScript" + item.ID.ToString(), "ScriptGroup")
//                    .Build();
//                //4、创建任务
//                var jobDetail = JobBuilder.Create<QuartzJobRunScriptTask>()
//                        .WithIdentity("jobScript" + item.ID.ToString(), "JobGroup")
//                        .Build();
//                //5、将触发器和任务器绑定到调度器中
//                await _scheduler.ScheduleJob(jobDetail, trigger);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
