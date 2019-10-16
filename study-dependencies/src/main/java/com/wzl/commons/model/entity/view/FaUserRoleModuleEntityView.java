package com.wzl.commons.model.entity.view;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

/**
 * 系统角色
 */
@Data
@Table("fa_module m \n" +
        "LEFT JOIN fa_role_module rm on rm.MODULE_ID=m.ID \n" +
        "LEFT JOIN fa_user_role ur on rm.ROLE_ID=ur.ROLE_ID \n" +
        "LEFT JOIN fa_role r on r.ID=ur.ROLE_ID ")
public class FaUserRoleModuleEntityView
{
    /**
     * ROLE_ID
     */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Required
    @Display(Name = "ROLE_ID")
    @Column("ur.ROLE_ID")
    public int roleId ;


    /**
     * 角色名
     */
    @StringLength(80)
    @Display(Name = "角色名")
    @Column("r.`NAME`")
    public String roleName ;

    /**
     * 类型
     */
    @Display(Name = "类型")
    @Column("r.`TYPE`")
    public int roleType ;


    /**
     * USER_ID
     */
    @Required
    @Key
    @Display(Name = "USER_ID")
    @Column("ur.USER_ID")
    public int userId ;

    /**
     * 用户姓名
     */
    @StringLength(80)
    @Display(Name = "用户姓名")
    @Column("u.`NAME`")
    public String userName ;
}

