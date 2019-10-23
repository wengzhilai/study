package com.quartz.jobs;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.dependencies.mybatis.service.MyBatisService;
import com.quartz.server.ScriptService;
import com.quartz.server.ScriptTaskLogService;
import com.quartz.server.ScriptTaskService;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaScriptEntity;
import com.wzl.commons.model.entity.FaScriptTaskEntity;
import com.wzl.commons.model.entity.FaScriptTaskLogEntity;
import com.wzl.commons.utlity.LogHelper;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 执行具体任务
 */
public class QuartzJobRunScriptTask implements Job {
    @Autowired
    Scheduler scheduler;

    @Autowired
    ScriptService scriptService;

    @Autowired
    ScriptTaskService scriptTaskService;

    @Autowired
    ScriptTaskLogService scriptTaskLogService;

    @Autowired
    MyBatisService<FaScriptTaskEntity> dapper;

    @Override
    public void execute(JobExecutionContext context) {
        // var jobData = context.JobDetail.JobDataMap;//获取Job中的参数
        JobDataMap triggerData = context.getTrigger().getJobDataMap();//获取Trigger中的参数
        // 当Job中的参数和Trigger中的参数名称一样时，用 context.MergedJobDataMap获取参数时，Trigger中的值会覆盖Job中的值。
        // var data = context.MergedJobDataMap;//获取Job和Trigger中合并的参数
        int scriptId = triggerData.getIntFromString("scriptId");
        FaScriptEntity script = scriptService.singleByKey(scriptId);
        if (script != null) {
            FaScriptTaskEntity addEnt = new FaScriptTaskEntity();
            addEnt.bodyHash = SecureUtil.md5(script.bodyText);
            addEnt.bodyText = script.bodyText;
            addEnt.logType = "0";
            addEnt.returnCode = "";
            addEnt.runArgs = script.runArgs;
            addEnt.runData = script.runData;
            addEnt.runState = "等待";
            addEnt.scriptId = scriptId;
            addEnt.startTime = new Date();
            ResultObj<Integer> taskId = scriptTaskService.save(new DtoSave<>(addEnt));
            if (!taskId.success) {
                LogHelper.writeErrorLog(this.getClass(), "添加任务出错：" + taskId.msg);
                return;
            }

            String[] sqlList = script.bodyText.split(";");

            for (String item : sqlList) {
                {
                    int opNum = 0;
                    FaScriptTaskLogEntity log = new FaScriptTaskLogEntity();
                    log.scriptTaskId = taskId.data;
                    log.logTime = new Date();
                    log.sqlText = item;
                    log.message = Convert.toStr(opNum);

                    try {
                        opNum = dapper.exec(item);
                        log.logType = "1";
                        log.message = Convert.toStr(opNum);
                    } catch (Exception e) {
                        LogHelper.writeErrorLog(this.getClass(), String.format("执行%1$s任务出错：%2$s", item, e.getMessage()));
                        log.logType = "2";
                        log.message = e.getMessage();
                    }
                    ResultObj<Integer> op = scriptTaskLogService.save(new DtoSave<>(log));

                }
            }
        }
    }
}
