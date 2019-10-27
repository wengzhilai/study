package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.model.mynum.TableColumnType;
import com.wzl.commons.retention.*;

import java.util.Date;

/**
 * 表名
 */
@Table("fa_table_column")
public class FaTableColumnEntity {


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
    * TABLE_TYPE_ID
    */
    @Display(Name = "TABLE_TYPE_ID")
    @Column("TABLE_TYPE_ID")
    public int tableTypeId;

    /**
    * 表名
    */
    @Display(Name = "表名")
    @Column("NAME")
    public String name;

    /**
    * 数据库中列名
    */
    @Display(Name = "数据库中列名")
    @Column("COLUMN_NAME")
    public String columnName;

    /**
    * 介绍
    */
    @Display(Name = "介绍")
    @Column("INTRODUCE")
    public String introduce;

    /**
    * 状态
    */
    @Display(Name = "状态")
    @Column("STAUTS")
    public String stauts;

    /**
    * ORDER_INDEX
    */
    @Display(Name = "ORDER_INDEX")
    @Column("ORDER_INDEX")
    public int orderIndex;

    /**
    * 列类型
    */
    @Display(Name = "列类型")
    @Column("COLUMN_TYPE")
    public TableColumnType columnType;

    /**
    * COLUMN_LONG
    */
    @Display(Name = "COLUMN_LONG")
    @Column("COLUMN_LONG")
    public int columnLong;

    /**
    * IS_REQUIRED
    */
    @Display(Name = "IS_REQUIRED")
    @Column("IS_REQUIRED")
    public int isRequired;

    /**
    * DEFAULT_VALUE
    */
    @Display(Name = "DEFAULT_VALUE")
    @Column("DEFAULT_VALUE")
    public String defaultValue;

    /**
    * COLUMN_TYPE_CFG
    */
    @Display(Name = "COLUMN_TYPE_CFG")
    @Column("COLUMN_TYPE_CFG")
    public String columnTypeCfg;

    /**
    * AUTHORITY
    */
    @Display(Name = "AUTHORITY")
    @Column("AUTHORITY")
    public int authority;
}
/*
select 
  ID id,
  TABLE_TYPE_ID tableTypeId,
  NAME name,
  COLUMN_NAME columnName,
  INTRODUCE introduce,
  STAUTS stauts,
  ORDER_INDEX orderIndex,
  COLUMN_TYPE columnType,
  COLUMN_LONG columnLong,
  IS_REQUIRED isRequired,
  DEFAULT_VALUE defaultValue,
  COLUMN_TYPE_CFG columnTypeCfg,
  AUTHORITY authority 
from fa_table_column
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "tableTypeId": {
    "title": "TABLE_TYPE_ID",
    "type": "int",
    "editable": true
  },
  "name": {
    "title": "表名",
    "type": "String",
    "editable": true
  },
  "columnName": {
    "title": "数据库中列名",
    "type": "String",
    "editable": true
  },
  "introduce": {
    "title": "介绍",
    "type": "String",
    "editable": true
  },
  "stauts": {
    "title": "状态",
    "type": "String",
    "editable": true
  },
  "orderIndex": {
    "title": "ORDER_INDEX",
    "type": "int",
    "editable": true
  },
  "columnType": {
    "title": "列类型",
    "type": "String",
    "editable": true
  },
  "columnLong": {
    "title": "COLUMN_LONG",
    "type": "int",
    "editable": true
  },
  "isRequired": {
    "title": "IS_REQUIRED",
    "type": "int",
    "editable": true
  },
  "defaultValue": {
    "title": "DEFAULT_VALUE",
    "type": "String",
    "editable": true
  },
  "columnTypeCfg": {
    "title": "COLUMN_TYPE_CFG",
    "type": "String",
    "editable": true
  },
  "authority": {
    "title": "AUTHORITY",
    "type": "int",
    "editable": true
  }
}
*/
