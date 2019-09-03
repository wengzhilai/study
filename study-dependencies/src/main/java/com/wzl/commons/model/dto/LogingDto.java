package com.wzl.commons.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户登录")
public class LogingDto {

    public LogingDto(){}
    /**
     *
     * @param _loginName
     * @param _password
     */
    public LogingDto(String _loginName,String _password){
        this.loginName=_loginName;
        this.password=_password;
    }

    /**
    * 用户姓名
    */
    @ApiModelProperty(value = "用户姓名")
    public String userName ;
    /**
    * 登录名
    */
    @ApiModelProperty(value = "登录名")
    public String loginName ;
    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    public String password ;
    /**
    * 手机串号
    */
    @ApiModelProperty(value = "手机串号")
    public String imei ;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    public String version ;
    /**
    * 短信验证码
    */
    @ApiModelProperty(value = "短信验证码")
    public String code ;
    /**
    * 微信
    */
    @ApiModelProperty(value = "微信")
    public String openid ;
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    public String userId ;
    /**
    * 推荐码
    */
    @ApiModelProperty(value = "推荐码")
    public String pollCode ;
    /**
    * 类型
    */
    @ApiModelProperty(value = "类型")
    public int type ;
}
