package com.user.consumer.controller.impl;

import com.user.consumer.controller.BaseController;
import com.user.consumer.controller.LoginController;
import com.user.consumer.feign.LoginService;
import com.user.consumer.feign.TestService;
import com.user.consumer.model.entity.FaUserEntity;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("Login")
public class LoginControllerImpl extends BaseController implements LoginController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoginService login;

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public ResultObj<FaUserEntity> userLogin(@RequestBody LogingDto inEnt) {
        return login.userLogin(inEnt);
    }

    @Override
    public ResultObj<Integer> loginReg(@RequestBody LogingDto inEnt) {
        return null;
    }

    @Override
    public Result resetPassword(@RequestBody ResetPasswordDto inEnt) {
        return null;
    }

    @Override
    public Result deleteUser(String userName) {
        return null;
    }

    @Override
    public ResultObj<Boolean> userEditPwd(EditPwdDto inEnt) {
        return null;
    }

    @Override
    public Result userEditLoginName(String oldName, String newName) {
        return null;
    }
}
