package com.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadTaskJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());
        System.out.println( strTime + ":Hello World！LoadTaskJob");

//        var AllTask = await _scritp.ScriptList(new DtoSearch<FaScriptEntity>()
//        {
//            FilterList = x => x.STATUS == "正常",
//            PageIndex = 1,
//            PageSize = 1000
//        });
//
//        foreach (var item in AllTask)
//        {
//            GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher<TriggerKey>.GroupEquals("ScriptGroup");
//            var triggerList = await _scheduler.GetTriggerKeys(matcherTrigger);
//            var triggerKey = triggerList.SingleOrDefault(x => x.Name == "triggerScript" + item.ID.ToString());
//            if (string.IsNullOrEmpty(item.RUN_WHEN)) continue;
//            //表示任务存在
//            if (triggerKey != null)
//            {
//                ICronTrigger trigger = (ICronTrigger)_scheduler.GetTrigger(triggerKey);
//                IJobDetail job = await _scheduler.GetJobDetail(trigger.JobKey);
//                if (trigger.CronExpressionString != item.RUN_WHEN)
//                {
//                    // logger.InfoFormat("脚本服务 修改触发器【{0}】的时间表达式【{1}】为【{2}】", trigger.Key.Name, trigger.CronExpressionString, t.RUN_WHEN);
//                    trigger.CronExpressionString = item.RUN_WHEN;
//                    await _scheduler.DeleteJob(trigger.JobKey);
//                    await _scheduler.ScheduleJob(job, trigger);
//                }
//            }
//            else
//            {
//                //3、创建一个触发器
//                var trigger = TriggerBuilder.Create()
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
//            }
//        }
    }
}
