package com.user.provider.model.entity.view;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

/**
 * 系统角色
 */
@Data
@Table("fa_user_role ur LEFT JOIN fa_user u on u.ID=ur.USER_ID LEFT JOIN fa_role r on r.ID=ur.ROLE_ID ")
public class FaUserRoleEntityView
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
    public Integer type ;


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

