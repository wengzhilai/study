package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;
import java.util.List;

/**
 * 模块
 */
@Table("FA_MODULE")
public class FaModuleEntity {

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
    * 上级
    */
    @Display(Name = "上级")
    @Column("PARENT_ID")
    public Integer parentId;

    /**
    * 模块名
    */
    @Display(Name = "模块名")
    @Column("NAME")
    public String name;

    /**
    * 地址
    */
    @Display(Name = "地址")
    @Column("LOCATION")
    public String location;

    /**
    * 代码
    */
    @Display(Name = "代码")
    @Column("CODE")
    public String code;

    /**
    * 调试
    */
    @Required
    @Display(Name = "调试")
    @Column("IS_DEBUG")
    public Integer isDebug;

    /**
    * 隐藏
    */
    @Required
    @Display(Name = "隐藏")
    @Column("IS_HIDE")
    public Integer isHide;

    /**
    * 排序
    */
    @Required
    @Display(Name = "排序")
    @Column("SHOW_ORDER")
    public Integer showOrder;

    /**
    * 描述
    */
    @Display(Name = "描述")
    @Column("DESCRIPTION")
    public String description;

    /**
    * 图片
    */
    @Display(Name = "图片")
    @Column("IMAGE_URL")
    public String imageUrl;

    /**
    * 桌面角色
    */
    @Display(Name = "桌面角色")
    @Column("DESKTOP_ROLE")
    public String desktopRole;

    /**
    * 宽
    */
    @Display(Name = "宽")
    @Column("W")
    public Integer w;

    /**
    * 高
    */
    @Display(Name = "高")
    @Column("H")
    public Integer h;

    /**
     * 所有子项
     */
    public List<FaModuleEntity> children;
}
/*
select 
  ID id,
  PARENT_ID parentId,
  NAME name,
  LOCATION location,
  CODE code,
  IS_DEBUG isDebug,
  IS_HIDE isHide,
  SHOW_ORDER showOrder,
  DESCRIPTION description,
  IMAGE_URL imageUrl,
  DESKTOP_ROLE desktopRole,
  W w,
  H h 
from FA_MODULE
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "parentId": {
    "title": "上级",
    "type": "int",
    "editable": true
  },
  "name": {
    "title": "模块名",
    "type": "String",
    "editable": true
  },
  "location": {
    "title": "地址",
    "type": "String",
    "editable": true
  },
  "code": {
    "title": "代码",
    "type": "String",
    "editable": true
  },
  "isDebug": {
    "title": "调试",
    "type": "String",
    "editable": true
  },
  "isHide": {
    "title": "隐藏",
    "type": "String",
    "editable": true,
    "editor": {
"type": "list",
"config": {
"list": [
{
"value": "1",
"title": "是"
},
{
"value": "0",
"title": "否"
}
]
}
}
  },
  "showOrder": {
    "title": "排序",
    "type": "String",
    "editable": true
  },
  "description": {
    "title": "描述",
    "type": "String",
    "editable": true
  },
  "imageUrl": {
    "title": "图片",
    "type": "String",
    "editable": true
  },
  "desktopRole": {
    "title": "桌面角色",
    "type": "String",
    "editable": true
  },
  "w": {
    "title": "宽",
    "type": "int",
    "editable": true
  },
  "h": {
    "title": "高",
    "type": "int",
    "editable": true
  }
}
*/
