package com.wzl.commons.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改密码
 */
@Data
@ApiModel("修改密码")
public class EditPwdDto {
    /**
    * 用户登录名
    */
    @ApiModelProperty("用户登录名")
    public String loginName ;

    /**
    ** 旧密码
    **/
    @ApiModelProperty("旧密码")
    public String oldPwd ;
    /**
    ** 新密码
    **/
    @ApiModelProperty("新密码")
    public String newPwd ;
    /**
    ** 重置新密码
    **/
    @ApiModelProperty("重置新密码")
    public String reNewPwd ;
}
