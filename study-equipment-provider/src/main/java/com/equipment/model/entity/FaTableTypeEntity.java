package com.equipment.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import java.util.Date;

import java.util.List;

/**
* 自定义表的类型
*/
@Table("fa_table_type")
public class FaTableTypeEntity {

    /**
    * ID
    */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Display(Name = "ID")
    @Column("ID")
    public int id;

    /**
    * 表别名
    */
    @StringLength(50)
    @Display(Name = "表别名")
    @Column("NAME")
    public String name;

    /**
    * 数据库中表名
    */
    @StringLength(50)
    @Display(Name = "数据库中表名")
    @Column("TABLE_NAME")
    public String tableName;

    /**
    * 介绍
    */
    @StringLength(50)
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
    * 状态,禁用，启用
    */
    @StringLength(15)
    @Display(Name = "状态")
    @Column("STAUTS")
    public String stauts;

    /**
    * 表的所有列
    */
    public List<FaTableColumnEntity> AllColumns;


}
