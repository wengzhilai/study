package com.wjbjp.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("重置密码")
public class ResetPasswordDto {
    /**
     * 验证码
     **/
    @ApiModelProperty("验证码")
    public String verifyCode;
    /**
     * 登录码
     **/
    @ApiModelProperty("登录码")
    public String loginName;
    /**
     * 新密码
     **/
    @ApiModelProperty("新密码")
    public String newPwd;

}
