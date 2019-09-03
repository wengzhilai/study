package com.wjbjp.model.entity;

import com.wjbjp.model.mynum.DatabaseGeneratedOption;
import com.wjbjp.retention.*;
import java.util.List;

/**
 * 系统角色
 */
@Table("fa_role")
public class FaRoleEntity {

    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.None)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;
    /**
     * 角色名
     */
    @Display(Name = "角色名")
    @Column("NAME")
    public String name;
    /**
     * 备注
     */
    @StringLength(255)
    @Display(Name = "备注")
    @Column("REMARK")
    public String remark;
    /**
     * 类型
     */
    @Display(Name = "类型")
    @Column("TYPE")
    public Integer type;
    /**
     * 模块ID集合
     */
    public List<Integer> moduleIdStr;
}