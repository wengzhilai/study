package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;

/**
 * 任务日志
 */
@Table("FA_SCRIPT_TASK_LOG")
public class FaScriptTaskLogEntity {


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
    * 口径任务ID
    */
    @Required
    @Display(Name = "口径任务ID")
    @Column("SCRIPT_TASK_ID")
    public int scriptTaskId;

    /**
    * 记录时间
    */
    @Required
    @Display(Name = "记录时间")
    @Column("LOG_TIME")
    public Date logTime;

    /**
    * 日志级别
    */
    @Required
    @Display(Name = "日志级别")
    @Column("LOG_TYPE")
    public String logType;

    /**
    * 日志说明
    */
    @Display(Name = "日志说明")
    @Column("MESSAGE")
    public String message;

    /**
    * SQL内容
    */
    @Display(Name = "SQL内容")
    @Column("SQL_TEXT")
    public String sqlText;
}
/*
select 
  ID id,
  SCRIPT_TASK_ID scriptTaskId,
  LOG_TIME logTime,
  LOG_TYPE logType,
  MESSAGE message,
  SQL_TEXT sqlText 
from FA_SCRIPT_TASK_LOG
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "scriptTaskId": {
    "title": "口径任务ID",
    "type": "int",
    "editable": true
  },
  "logTime": {
    "title": "记录时间",
    "type": "Date",
    "editable": true
  },
  "logType": {
    "title": "日志级别",
    "type": "String",
    "editable": true
  },
  "message": {
    "title": "日志说明",
    "type": "String",
    "editable": true
  },
  "sqlText": {
    "title": "SQL内容",
    "type": "String",
    "editable": true
  }
}
*/
