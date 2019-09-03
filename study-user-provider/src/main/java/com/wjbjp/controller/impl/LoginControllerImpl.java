package com.wjbjp.controller.impl;

import com.wjbjp.controller.LoginController;
import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.EditPwdDto;
import com.wjbjp.model.dto.LogingDto;
import com.wjbjp.model.dto.ResetPasswordDto;
import com.wjbjp.model.entity.FaUserEntity;
import com.wjbjp.server.LoginService;
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
