package com.user.consumer.feign;

import com.user.consumer.feign.impl.LoginServiceImpl;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider",url = "http://localhost:9001", fallback = LoginServiceImpl.class)
public interface LoginService {

    /**
     * 注册账号
     * 1、添加登录工号
     * 2、添加用户
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/Login/loginReg")
    ResultObj<Integer> loginReg(@RequestBody LogingDto inEnt);


    /**
    * 用户登录
    * 只验证用户账号
    */
    @GetMapping(value = "/Login/userLogin")
    ResultObj<FaUserEntity> userLogin(@RequestBody LogingDto inEnt);

    /**
     * 根据账号和密码获取账号
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/Login/getSingleByPwd")
    ResultObj<FaUserEntity> getSingleByPwd(@RequestBody LogingDto inEnt);

    /**
    * 重置用户密码
    */
    @GetMapping(value = "/Login/resetPassword")
    Result resetPassword(@RequestBody ResetPasswordDto inEnt);

    /**
    * 修改用户密码
    */
    @GetMapping(value = "/Login/userEditPwd")
    ResultObj<Boolean> userEditPwd(@RequestBody EditPwdDto inEnt);


    /**
     * 修改用户
     * @param inDto
     * @return
     */
    @GetMapping(value = "/Login/userEditUser")
    Result userEditUser(DtoSave<FaUserEntity> inDto);



}
