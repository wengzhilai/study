package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

import java.util.Date;

/**
 * 口径脚本
 */
@Table("FA_SCRIPT")
@Data
public class FaScriptEntity {

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
    * 代码
    */
    @Required
    @Display(Name = "代码")
    @Column("CODE")
    public String code;

    /**
    * 名称
    */
    @Required
    @Display(Name = "名称")
    @Column("NAME")
    public String name;

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
    * 状态
    */
    @Display(Name = "状态")
    @Column("STATUS")
    public String status;

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
    * 是否是组
    */
    @Required
    @Display(Name = "是否是组")
    @Column("IS_GROUP")
    public String isGroup;
}
/*
select 
  Code code,
  ID id,
  CODE code,
  NAME name,
  BODY_TEXT bodyText,
  BODY_HASH bodyHash,
  RUN_WHEN runWhen,
  RUN_ARGS runArgs,
  RUN_DATA runData,
  STATUS status,
  DISABLE_REASON disableReason,
  SERVICE_FLAG serviceFlag,
  REGION region,
  IS_GROUP isGroup 
from FA_SCRIPT
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
  "code": {
    "title": "代码",
    "type": "String",
    "editable": true
  },
  "name": {
    "title": "名称",
    "type": "String",
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
  "status": {
    "title": "状态",
    "type": "String",
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
  "isGroup": {
    "title": "是否是组",
    "type": "String",
    "editable": true
  }
}
*/
