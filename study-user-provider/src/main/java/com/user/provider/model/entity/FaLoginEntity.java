package com.user.provider.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

import java.util.Date;

@Data
@Table("fa_login")
public class FaLoginEntity {

    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Display(Name = "ID")
    @Column("ID")
    public int id;


    @StringLength(20)
    @Display(Name = "登录名")
    @Column("LOGIN_NAME")
    public String loginName;
    /**
    * 密码
    */
    @StringLength(255)
    @Display(Name = "密码")
    @Column("PASSWORD")
    public String password;
    /**
    * 电话
    */
    @StringLength(20)
    @Display(Name = "电话")
    @Column("PHONE_NO")
    public String phoneNo;
    /**
    * 邮件
    */
    @StringLength(255)
    @Display(Name = "邮件")
    @Column("EMAIL_ADDR")
    public String emailAddr;
    /**
    * 验证码
    */
    @StringLength(10)
    @Display(Name = "验证码")
    @Column("VERIFY_CODE")
    public String verifyCode;
    /**
    * 验证时间
    */
    @Display(Name = "验证时间")
    @Column("VERIFY_TIME")
    public Date verifyTime;
    /**
    * 锁定
    */
    @Display(Name = "锁定")
    @Column("IS_LOCKED")
    public Integer isLocked;
    /**
    * 修改密码时间
    */
    @Display(Name = "修改密码时间")
    @Column("PASS_UPDATE_DATE")
    public Date passUpdateDate;
    /**
    * 锁定原因
    */
    @StringLength(255)
    @Display(Name = "锁定原因")
    @Column("LOCKED_REASON")
    public String lockedReason;

    /**
    * 失败次数
    */
    @Display(Name = "失败次数")
    @Column("FAIL_COUNT")
    public Integer failCount;

}


