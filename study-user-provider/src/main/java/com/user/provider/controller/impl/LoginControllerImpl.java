package com.user.provider.controller.impl;

import com.user.provider.controller.LoginController;
import com.user.provider.model.entity.FaUserEntity;
import com.user.provider.server.LoginService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Login")
public class LoginControllerImpl implements LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value="用户登录", notes="输入用户密码和账号")
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public ResultObj<FaUserEntity> userLogin(@RequestBody LogingDto inEnt) {
        ResultObj<FaUserEntity> reObj=loginService.userLogin(inEnt);
        return  reObj;
    }

    @Override
    @ApiOperation(value="用户注册", notes="输入登录账号和密码")
    public ResultObj<Integer> loginReg(LogingDto inEnt) {
        ResultObj<Integer> reObj=loginService.loginReg(inEnt);
        return  reObj;
    }

    @Override
    @ApiOperation(value="验证码修改密码", notes="验证码修改密码")
    public Result resetPassword(ResetPasswordDto inEnt) {
        Result reObj=loginService.resetPassword(inEnt);
        return  reObj;
    }

    @ApiOperation(value="删除用户", notes="删除用户的账号有用户信息")
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public Result deleteUser(String userName) {
        Result reObj=loginService.deleteUser(userName);
        return  reObj;
    }

    @ApiOperation(value="使用密码修改密码", notes="使用密码修改密码")
    @RequestMapping(value = "userEditPwd", method = RequestMethod.POST)
    public ResultObj<Boolean> userEditPwd(EditPwdDto inEnt) {
        ResultObj<Boolean> reObj=loginService.userEditPwd(inEnt);
        return  reObj;
    }

    @ApiOperation(value="修改登录账号", notes="")
    @RequestMapping(value = "userEditLoginName", method = RequestMethod.POST)
    public Result userEditLoginName(String oldName, String newName) {
        Result reObj=loginService.userEditLoginName(oldName,newName);
        return  reObj;
    }

}
