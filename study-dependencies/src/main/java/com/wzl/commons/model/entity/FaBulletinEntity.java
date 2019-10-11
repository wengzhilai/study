package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;

/**
 * 公告
 */
@Table("fa_bulletin")
public class FaBulletinEntity {


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
    * 标题
    */
    @Required
    @Display(Name = "标题")
    @Column("TITLE")
    public String title;

    /**
    * 图片
    */
    @Display(Name = "图片")
    @Column("PIC")
    public String pic;

    /**
    * 公告类型
    */
    @Display(Name = "公告类型")
    @Column("TYPE_CODE")
    public String typeCode;

    /**
    * 内容
    */
    @Display(Name = "内容")
    @Column("CONTENT")
    public String content;

    /**
    * 发布人ID
    */
    @Display(Name = "发布人ID")
    @Column("USER_ID")
    public int userId;

    /**
    * 发布人
    */
    @Required
    @Display(Name = "发布人")
    @Column("PUBLISHER")
    public String publisher;

    /**
    * 生效时间
    */
    @Required
    @Display(Name = "生效时间")
    @Column("ISSUE_DATE")
    public Date issueDate;

    /**
    * 显示
    */
    @Required
    @Display(Name = "显示")
    @Column("IS_SHOW")
    public String isShow;

    /**
    * 重要
    */
    @Required
    @Display(Name = "重要")
    @Column("IS_IMPORT")
    public String isImport;

    /**
    * 置顶
    */
    @Required
    @Display(Name = "置顶")
    @Column("IS_URGENT")
    public String isUrgent;

    /**
    * 自动打开
    */
    @Required
    @Display(Name = "自动打开")
    @Column("AUTO_PEN")
    public String autoPen;

    /**
    * 创建时间
    */
    @Required
    @Display(Name = "创建时间")
    @Column("CREATE_TIME")
    public Date createTime;

    /**
    * 修改时间
    */
    @Required
    @Display(Name = "修改时间")
    @Column("UPDATE_TIME")
    public Date updateTime;

    /**
    * REGION
    */
    @Required
    @Display(Name = "REGION")
    @Column("REGION")
    public String region;
}
/*
select 
  ID id,
  TITLE title,
  PIC pic,
  TYPE_CODE typeCode,
  CONTENT content,
  USER_ID userId,
  PUBLISHER publisher,
  ISSUE_DATE issueDate,
  IS_SHOW isShow,
  IS_IMPORT isImport,
  IS_URGENT isUrgent,
  AUTO_PEN autoPen,
  CREATE_TIME createTime,
  UPDATE_TIME updateTime,
  REGION region 
from fa_bulletin
*/


/*
{
  "ID": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "TITLE": {
    "title": "标题",
    "type": "String",
    "editable": true
  },
  "PIC": {
    "title": "图片",
    "type": "String",
    "editable": true
  },
  "TYPE_CODE": {
    "title": "公告类型",
    "type": "String",
    "editable": true
  },
  "CONTENT": {
    "title": "内容",
    "type": "String",
    "editable": true
  },
  "USER_ID": {
    "title": "发布人ID",
    "type": "int",
    "editable": true
  },
  "PUBLISHER": {
    "title": "发布人",
    "type": "String",
    "editable": true
  },
  "ISSUE_DATE": {
    "title": "生效时间",
    "type": "Date",
    "editable": true
  },
  "IS_SHOW": {
    "title": "显示",
    "type": "String",
    "editable": true
  },
  "IS_IMPORT": {
    "title": "重要",
    "type": "String",
    "editable": true
  },
  "IS_URGENT": {
    "title": "置顶",
    "type": "String",
    "editable": true
  },
  "AUTO_PEN": {
    "title": "自动打开",
    "type": "String",
    "editable": true
  },
  "CREATE_TIME": {
    "title": "创建时间",
    "type": "Date",
    "editable": true
  },
  "UPDATE_TIME": {
    "title": "修改时间",
    "type": "Date",
    "editable": true
  },
  "REGION": {
    "title": "REGION",
    "type": "String",
    "editable": true
  }
}
*/
