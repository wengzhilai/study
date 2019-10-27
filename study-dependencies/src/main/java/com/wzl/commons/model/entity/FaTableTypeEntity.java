package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;
import java.util.List;

/**
 * 自定义表
 */
@Table("fa_table_type")
public class FaTableTypeEntity {


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
    * 表名
    */
    @Display(Name = "表名")
    @Column("NAME")
    public String name;

    /**
    * 数据库中表名
    */
    @Display(Name = "数据库中表名")
    @Column("TABLE_NAME")
    public String tableName;

    /**
    * 介绍
    */
    @Display(Name = "介绍")
    @Column("INTRODUCE")
    public String introduce;

    /**
    * 添加时间
    */
    @Display(Name = "添加时间")
    @Column("ADD_TIME")
    public Date addTime;

    /**
    * 状态 1正常，2停用
    */
    @Display(Name = "状态 1正常，2停用")
    @Column("STAUTS")
    public String stauts;

    /**
     * 表的所有列
     */
    public List<FaTableColumnEntity> allColumns;
}
/*
select 
  ID id,
  NAME name,
  TABLE_NAME tableName,
  INTRODUCE introduce,
  ADD_TIME addTime,
  STAUTS stauts 
from fa_table_type
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "name": {
    "title": "表名",
    "type": "String",
    "editable": true
  },
  "tableName": {
    "title": "数据库中表名",
    "type": "String",
    "editable": true
  },
  "introduce": {
    "title": "介绍",
    "type": "String",
    "editable": true
  },
  "addTime": {
    "title": "添加时间",
    "type": "Date",
    "editable": true
  },
  "stauts": {
    "title": "状态 1正常，2停用",
    "type": "String",
    "editable": true
  }
}
*/
