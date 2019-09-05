package com.wjbjp.model.entity;
import java.util.Date;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

import javax.management.openmbean.ArrayType;

@Data
@Table("fa_user")
public class FaUserEntity {
    /**
    * ID
    */
    @Key
    @Required
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Column("ID")
    public int id;
    /**
    * 姓名
    */
    @StringLength(80)
    @Display(Name = "姓名")
    @Column("NAME")
    public String name;
    /**
    * 登录名
    */
    @StringLength(20)
    @Display(Name = "登录名")
    @Column("LOGIN_NAME")
    public String loginName;
    /**
    * 头像图片
    */
    @Display(Name = "头像图片")
    @Column("ICON_FILES_ID")
    public int iconFilesId;
    /**
    * 归属地
    */
    @Required
    @Display(Name = "归属地")
    @Column("DISTRICT_ID")
    public int districtId;
    /**
    * 锁定
    */
    @Display(Name = "锁定")
    @Column("IS_LOCKED")
    public int isLocked;
    /**
    * 创建时间
    */
    @Display(Name = "创建时间")
    @Column("CREATE_TIME")
    public Date createTime;
    /**
    * 登录次数
    */
    @Display(Name = "登录次数")
    @Column("LOGIN_COUNT")
    public int loginCount;
    /**
    * 最后登录时间
    */
    @Display(Name = "最后登录时间")
    @Column("LAST_LOGIN_TIME")
    public Date lastLoginTime;
    /**
    * 最后离开时间
    */
    @Display(Name = "最后离开时间")
    @Column("LAST_LOGOUT_TIME")
    public Date lastLogoutTime;
    /**
    * 最后活动时间
    */
    @Display(Name = "最后活动时间")
    @Column("LAST_ACTIVE_TIME")
    public Date lastActiveTime;
    /**
    * 备注
    */
    @StringLength(2000)
    @Display(Name = "备注")
    @Column("REMARK")
    public String remark;


    /**
    * 是管理管理员
    */
    public boolean isAdmin;

    /**
    * 是普通管理员
    */
    public boolean isLeader;
    /**
    * 用户角色
    */
    public int roleIdList;
    /**
    * 可编辑的用户ID
    */
    public ArrayType<Integer> canEditIdList;
}
