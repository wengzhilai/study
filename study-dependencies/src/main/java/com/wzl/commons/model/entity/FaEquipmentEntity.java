package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;

/**
 * 设备类型
 */
@Table("fa_equipment")
public class FaEquipmentEntity {


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
    * 设备名称
    */
    @Display(Name = "设备名称")
    @Column("NAME")
    public String name;

    /**
    * 上级设备
    */
    @Display(Name = "上级设备")
    @Column("PARENT_ID")
    public int parentId;

    /**
    * 表类型
    */
    @Display(Name = "表类型")
    @Column("TABLE_TYPE_ID")
    public int tableTypeId;

    /**
    * 说明
    */
    @Display(Name = "说明")
    @Column("INTRODUCE")
    public String introduce;

    /**
    * 状态，1正常，2停用
    */
    @Display(Name = "状态，1正常，2停用")
    @Column("STAUTS")
    public String stauts;
}
/*
select 
  ID id,
  NAME name,
  PARENT_ID parentId,
  TABLE_TYPE_ID tableTypeId,
  INTRODUCE introduce,
  STAUTS stauts 
from fa_equipment
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "name": {
    "title": "设备名称",
    "type": "String",
    "editable": true
  },
  "parentId": {
    "title": "上级设备",
    "type": "int",
    "editable": true
  },
  "tableTypeId": {
    "title": "表类型",
    "type": "int",
    "editable": true
  },
  "introduce": {
    "title": "说明",
    "type": "String",
    "editable": true
  },
  "stauts": {
    "title": "状态，1正常，2停用",
    "type": "String",
    "editable": true
  }
}
*/
