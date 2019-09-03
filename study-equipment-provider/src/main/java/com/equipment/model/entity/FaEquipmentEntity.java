package com.equipment.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

@Table("fa_equipment")
public class FaEquipmentEntity {

    /// <summary>
    /// ID
    /// </summary>
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.None)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;

    /// <summary>
    /// 设备名
    /// </summary>
    @Required
    @StringLength(50)
    @Display(Name = "设备名")
    @Column("NAME")
    public String name;


    /// <summary>
    /// 上级ID
    /// </summary>
    /// <value></value>
    @Display(Name = "上级ID")
    @Column("PARENT_ID")
    public Integer parentId;


    /// <summary>
    /// 自定义表ID
    /// </summary>
    /// <value></value>
    @Display(Name = "自定义表ID")
    @Column("TABLE_TYPE_ID")
    public int tableTypeId;

    /// <summary>
    /// 介绍
    /// </summary>
    @Required
    @StringLength(50)
    @Display(Name = "介绍")
    @Column("INTRODUCE")
    public String introduce;

    /// <summary>
    /// 状态,禁用，启用
    /// </summary>
    @StringLength(15)
    @Display(Name = "状态")
    @Column("STAUTS")
    public String stauts;
}
