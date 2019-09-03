package com.wjbjp.model.entity;

import com.wjbjp.retention.*;
import lombok.Data;

@Data
@Table("fa_login")
public class FaLoginEntity {
    /**
     * ID
     */
    @Key
    @DatabaseGenerated(DatabaseGenerated.DatabaseGeneratedOption.Computed)
    @Display(Name = "ID")
    @Column("ID")
    public int id;
    /**
     * 登录名
     */
    @Display(Name = "登录名")
    @Column("LOGIN_NAME")
    public String login_Name;
    /**
     * 密码
     */
    @Display(Name = "密码")
    @Column("PASSWORD")
    public String password ;

}
