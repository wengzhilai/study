package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;

/**
 * 任务
 */
@Table("FA_SCRIPT_TASK")
public class FaScriptTaskEntity {

    /**
    * ID
    */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;

    /**
    * 口径脚本ID
    */
    @Required
    @Display(Name = "口径脚本ID")
    @Column("SCRIPT_ID")
    public int scriptId;

    /**
    * 任务脚本
    */
    @Required
    @Display(Name = "任务脚本")
    @Column("BODY_TEXT")
    public String bodyText;

    /**
    * 脚本哈希值
    */
    @Required
    @Display(Name = "脚本哈希值")
    @Column("BODY_HASH")
    public String bodyHash;

    /**
    * 运行状态
    */
    @Required
    @Display(Name = "运行状态")
    @Column("RUN_STATE")
    public String runState;

    /**
    * 时间表达式
    */
    @Display(Name = "时间表达式")
    @Column("RUN_WHEN")
    public String runWhen;

    /**
    * 脚本参数
    */
    @Display(Name = "脚本参数")
    @Column("RUN_ARGS")
    public String runArgs;

    /**
    * 运行时间
    */
    @Required
    @Display(Name = "运行时间")
    @Column("RUN_DATA")
    public String runData;

    /**
    * 日志类型
    */
    @Display(Name = "日志类型")
    @Column("LOG_TYPE")
    public String logType;

    /**
    * 任务类型
    */
    @Display(Name = "任务类型")
    @Column("DSL_TYPE")
    public String dslType;

    /**
    * 任务状态
    */
    @Display(Name = "任务状态")
    @Column("RETURN_CODE")
    public String returnCode;

    /**
    * 开始时间
    */
    @Display(Name = "开始时间")
    @Column("START_TIME")
    public Date startTime;

    /**
    * 结束时间
    */
    @Display(Name = "结束时间")
    @Column("END_TIME")
    public Date endTime;

    /**
    * 禁用时间
    */
    @Display(Name = "禁用时间")
    @Column("DISABLE_DATE")
    public Date disableDate;

    /**
    * 禁用原因
    */
    @Display(Name = "禁用原因")
    @Column("DISABLE_REASON")
    public String disableReason;

    /**
    * 服务标识
    */
    @Display(Name = "服务标识")
    @Column("SERVICE_FLAG")
    public String serviceFlag;

    /**
    * REGION
    */
    @Display(Name = "REGION")
    @Column("REGION")
    public String region;

    /**
    * GROUP_ID
    */
    @Display(Name = "GROUP_ID")
    @Column("GROUP_ID")
    public int groupId;
}
/*
select 
  Code code,
  ID id,
  SCRIPT_ID scriptId,
  BODY_TEXT bodyText,
  BODY_HASH bodyHash,
  RUN_STATE runState,
  RUN_WHEN runWhen,
  RUN_ARGS runArgs,
  RUN_DATA runData,
  LOG_TYPE logType,
  DSL_TYPE dslType,
  RETURN_CODE returnCode,
  START_TIME startTime,
  END_TIME endTime,
  DISABLE_DATE disableDate,
  DISABLE_REASON disableReason,
  SERVICE_FLAG serviceFlag,
  REGION region,
  GROUP_ID groupId 
from FA_SCRIPT_TASK
*/


/*
{
  "code": {
    "title": "Name",
    "type": "String",
    "editable": true
  },
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "scriptId": {
    "title": "口径脚本ID",
    "type": "int",
    "editable": true
  },
  "bodyText": {
    "title": "任务脚本",
    "type": "String",
    "editable": true
  },
  "bodyHash": {
    "title": "脚本哈希值",
    "type": "String",
    "editable": true
  },
  "runState": {
    "title": "运行状态",
    "type": "String",
    "editable": true
  },
  "runWhen": {
    "title": "时间表达式",
    "type": "String",
    "editable": true
  },
  "runArgs": {
    "title": "脚本参数",
    "type": "String",
    "editable": true
  },
  "runData": {
    "title": "运行时间",
    "type": "String",
    "editable": true
  },
  "logType": {
    "title": "日志类型",
    "type": "String",
    "editable": true
  },
  "dslType": {
    "title": "任务类型",
    "type": "String",
    "editable": true
  },
  "returnCode": {
    "title": "任务状态",
    "type": "String",
    "editable": true
  },
  "startTime": {
    "title": "开始时间",
    "type": "Date",
    "editable": true
  },
  "endTime": {
    "title": "结束时间",
    "type": "Date",
    "editable": true
  },
  "disableDate": {
    "title": "禁用时间",
    "type": "Date",
    "editable": true
  },
  "disableReason": {
    "title": "禁用原因",
    "type": "String",
    "editable": true
  },
  "serviceFlag": {
    "title": "服务标识",
    "type": "String",
    "editable": true
  },
  "region": {
    "title": "REGION",
    "type": "String",
    "editable": true
  },
  "groupId": {
    "title": "GROUP_ID",
    "type": "int",
    "editable": true
  }
}
*/
