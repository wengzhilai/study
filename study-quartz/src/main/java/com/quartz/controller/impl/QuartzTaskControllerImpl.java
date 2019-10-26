package com.quartz.controller.impl;

import cn.hutool.core.convert.Convert;
import com.quartz.jobs.*;
import com.quartz.models.QuartzTaskModel;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.utlity.TypeChange;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@RestController
@RequestMapping("quartzTask")
public class QuartzTaskControllerImpl {

    @Autowired
    Scheduler scheduler ;

    @RequestMapping(value = "/isStarted", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public ResultObj<Boolean> isStarted() throws SchedulerException {
        ResultObj<Boolean> resultObj= new ResultObj<>(true);
        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.anyGroup();
        Set<TriggerKey> allTrigger = scheduler.getTriggerKeys(matcherTrigger);
        resultObj.data=allTrigger.size()>0;
        return resultObj;
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result start() throws SchedulerException {
        JobDetail jobDetail= scheduler.getJobDetail(new JobKey("jobLoadTask", "jobGroup"));
        if(jobDetail==null) {
            //创建JobDetail实例，并与HelloWordlJob类绑定
            JobDetail jobDetailTask = JobBuilder.newJob(LoadTaskJob.class).withIdentity("jobLoadTask", "jobGroup").build();
            //创建触发器Trigger实例(立即执行，每隔1S执行一次)
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("triggerJob", "triggerJobGroup")
                    .startNow()
                    .withSchedule(cronSchedule("1/5 * * * * ?"))
                    .build();
            //开始执行
            scheduler.scheduleJob(jobDetailTask, trigger1);
        }
        scheduler.start();
        return new Result(true);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result stop() throws SchedulerException {
        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.anyGroup();
        Set<TriggerKey> allTrigger = scheduler.getTriggerKeys(matcherTrigger);
        for (TriggerKey triggerKey : allTrigger) {
            QuartzTaskModel task = new QuartzTaskModel();
            CronTrigger jobTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobTrigger.getJobKey());
            scheduler.pauseTrigger(triggerKey);//停止触发器
            scheduler.unscheduleJob(triggerKey);//移除触发器
            scheduler.deleteJob(jobDetail.getKey());//删除任务
        }
        return new Result(true);
    }

    @RequestMapping(value = "/removeJob", method = RequestMethod.POST)
    @ApiOperation(value = "开启任务")
    public Result removeJob(@RequestBody DtoDo inEnt) throws SchedulerException {
        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.anyGroup();
        Set<TriggerKey> triggerList = scheduler.getTriggerKeys(matcherTrigger);
        for (TriggerKey triggerKey : triggerList) {
            if(triggerKey.getName().equals(inEnt.key)) {
                scheduler.pauseTrigger(triggerKey);//停止触发器
                Trigger trigger = scheduler.getTrigger(triggerKey);
                scheduler.deleteJob(trigger.getJobKey());//删除任务
                scheduler.unscheduleJob(triggerKey);//移除触发器
                return new Result(true);
            }
        }
        return new Result(false);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取任务列表")
    public ResultObj<QuartzTaskModel> list() throws SchedulerException {
        ResultObj<QuartzTaskModel> reObj = new ResultObj<>(true);
        reObj.dataList = new ArrayList<>();
        //1、通过调度工厂获得调度器
        GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.anyGroup();
        Set<TriggerKey> allTrigger = scheduler.getTriggerKeys(matcherTrigger);
        for (TriggerKey triggerKey : allTrigger) {
            QuartzTaskModel task = new QuartzTaskModel();
            CronTrigger jobTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobTrigger.getJobKey());
            task.keyName = jobTrigger.getKey().getName();
            task.keyGroup = jobTrigger.getKey().getGroup();
            task.jobDataListStr = TypeChange.objToString(jobTrigger.getJobDataMap());
//                task.calendarName = jobTrigger.getCalendarName();
            task.description = jobTrigger.getDescription();
            task.calendarName = jobTrigger.getCronExpression();
            if (jobTrigger.getEndTime() != null)
                task.endTime = TypeChange.dateToString(jobTrigger.getEndTime(), "yyyy-MM-dd HH-mm-ss");
            if (jobTrigger.getFinalFireTime() != null)
                task.finalFireTimeUtc = TypeChange.dateToString(jobTrigger.getFinalFireTime(), "yyyy-MM-dd HH-mm-ss");
            //返回下一次计划触发Quartz.ITrigger的时间
            if (jobTrigger.getNextFireTime() != null)
                task.nextFireTime = TypeChange.dateToString(jobTrigger.getNextFireTime(), "yyyy-MM-dd HH-mm-ss");
            //优先级
            task.priority = jobTrigger.getPriority();
            //触发器调度应该开始的时间
            task.startTimeUtc = TypeChange.dateToString(jobTrigger.getStartTime(), "yyyy-MM-dd HH-mm-ss");
            reObj.dataList.add(task);
        }
        return reObj;
    }
}
