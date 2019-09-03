package com.wzl.commons.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户登录请求", description="用户登录请求")
public class RegDto {
    /**
     * 登录名
     */
    @ApiModelProperty(value="用户名")
    public String name;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    public String pwd;
}
