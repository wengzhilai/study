package com.user.consumer.controller;


import com.user.consumer.model.entity.FaUserEntity;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;

public interface LoginController {
    ResultObj<FaUserEntity> userLogin(LogingDto inEnt);

    ResultObj<Integer> loginReg(LogingDto inEnt);

    Result resetPassword(ResetPasswordDto inEnt);

    Result deleteUser(String userName);

    ResultObj<Boolean> userEditPwd(EditPwdDto inEnt);

    Result userEditLoginName(String oldName, String newName);
}
