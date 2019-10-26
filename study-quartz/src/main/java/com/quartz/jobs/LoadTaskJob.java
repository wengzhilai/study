package com.quartz.jobs;

import com.quartz.component.BeanContext;
import com.quartz.server.ScriptService;
import com.wzl.commons.model.entity.FaScriptEntity;
import com.wzl.commons.utlity.TypeChange;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * 用于加载数据库资源，并添加任务
 */
public class LoadTaskJob implements Job {

    ScriptService scriptService;

    Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        scheduler=BeanContext.getBean(Scheduler.class);
        scriptService=BeanContext.getBean(ScriptService.class);
        String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());
        System.out.println(strTime + ":Scan Task Job");
        List<FaScriptEntity> allTask = scriptService.getNormalScript();
        for (FaScriptEntity item : allTask) {
            GroupMatcher<TriggerKey> matcherTrigger = GroupMatcher.groupEquals("ScriptTriggerGroup");
            Set<TriggerKey> triggerList = null;
            try {
                triggerList = scheduler.getTriggerKeys(matcherTrigger);

                // 否存在item的触发器任务
                Optional<TriggerKey> triggerKey = triggerList.stream().filter(x -> x.getName().equals("scriptTrigger_" + item.id)).findFirst();

                //表示任务存在
                if (triggerKey.isPresent()) {
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey.get());
                    JobDetail job = scheduler.getJobDetail(trigger.getJobKey());
                    //表示式有变化则重新加载表达式
                    if (!trigger.getCronExpression().equals(item.runWhen)) {
                        // logger.InfoFormat("脚本服务 修改触发器【{0}】的时间表达式【{1}】为【{2}】", trigger.Key.Name, trigger.CronExpressionString, t.RUN_WHEN);
                        trigger.getTriggerBuilder().withSchedule(cronSchedule(item.runWhen));
                        scheduler.deleteJob(trigger.getJobKey());
                        scheduler.scheduleJob(job, trigger);
                        System.out.println(String.format("表达式变化:原表达式[%1$s],现表达式[%2$s]",trigger.getCronExpression(),item.runWhen));
                    }
                }
                else {
                    //创建触发器Trigger实例(立即执行，每隔1S执行一次)
                    Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity("scriptTrigger_"+item.id, "ScriptTriggerGroup")
                            .startNow()
                            .usingJobData("scriptId", item.id)
                            .withSchedule(cronSchedule(item.runWhen))
                            .build();
                    JobDetail jobDetail = JobBuilder.newJob(QuartzJobRunScriptTask.class).withIdentity("scriptJob_" + item.id, "ScriptJobGroup").build();
                    //开始执行
                    scheduler.scheduleJob(jobDetail, trigger);
                    System.out.println(String.format("添加新的任务:%1$s", TypeChange.objToString(item)));
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
