package com.quartz.controller.impl;

import com.quartz.jobs.HelloWorldJob;
import com.quartz.models.QuartzTaskModel;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.utlity.TypeChange;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@RestController
@RequestMapping("quartzTask")
public class QuartzTaskControllerImpl {
//
//    @Autowired
//    JobFactory jobFactory;

    @Autowired
    SchedulerFactory schedulerFactory;
    @Autowired
    Scheduler scheduler ;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result start() throws SchedulerException {

        //创建JobDetail实例，并与HelloWordlJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class).withIdentity("job1", "jobGroup1")
                .build();
        //创建触发器Trigger实例(立即执行，每隔1S执行一次)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(cronSchedule("1/5 * * * * ?"))
                .build();
        //开始执行
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return new Result(true);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result stop() throws SchedulerException {
        scheduler.shutdown();
        return new Result(true);
    }

    @RequestMapping(value = "/removeJob", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result removeJob(DtoDo inEnt) throws SchedulerException {
        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.groupEquals(inEnt.key);
        Set<TriggerKey> triggerList = scheduler.getTriggerKeys(matcherTrigger);
        for (TriggerKey triggerKey : triggerList) {
            scheduler.pauseTrigger(triggerKey);//停止触发器
            scheduler.unscheduleJob(triggerKey);//移除触发器
            Trigger trigger= scheduler.getTrigger(triggerKey);
            scheduler.deleteJob(trigger.getJobKey());//删除任务
        }
        return new Result(true);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取任务列表")
    public ResultObj<QuartzTaskModel> list() {
        ResultObj<QuartzTaskModel> reObj = new ResultObj<>();
//        //1、通过调度工厂获得调度器
//        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.anyGroup();
//        Set<TriggerKey> allTrigger = scheduler.getTriggerKeys(matcherTrigger);
//
//        for (TriggerKey triggerKey : allTrigger) {
//            QuartzTaskModel task = new QuartzTaskModel();
//            Trigger jobTrigger = scheduler.getTrigger(triggerKey);
//            JobDetail jobDetail = scheduler.getJobDetail(jobTrigger.getJobKey());
//            task.keyName = jobTrigger.getKey().getName();
//            task.keyGroup = jobTrigger.getKey().getGroup();
//            task.jobDataListStr = TypeChange.objToString(jobTrigger.getJobDataMap());
//            task.calendarName = jobTrigger.getCalendarName();
//            task.description = jobTrigger.getDescription();
//            if (jobTrigger.EndTimeUtc != null) task.EndTime = jobTrigger.EndTimeUtc.Value.ToString("yyyy-MM-dd HH-mm-ss");
//            if (jobTrigger.FinalFireTimeUtc != null) task.FinalFireTimeUtc = jobTrigger.FinalFireTimeUtc.Value.ToString("yyyy-MM-dd HH-mm-ss");
//            //返回下一次计划触发Quartz.ITrigger的时间
//            if (jobTrigger.GetNextFireTimeUtc() != null) task.NextFireTime = jobTrigger.GetNextFireTimeUtc().Value.ToString("yyyy-MM-dd HH-mm-ss");
//            //优先级
//            task.Priority = jobTrigger.Priority;
//            //触发器调度应该开始的时间
//            task.StartTimeUtc = jobTrigger.StartTimeUtc.ToString("yyyy-MM-dd HH-mm-ss");
//            reObj.DataList.Add(task);
//        }
//        return reObj;
        return reObj;
    }
}
