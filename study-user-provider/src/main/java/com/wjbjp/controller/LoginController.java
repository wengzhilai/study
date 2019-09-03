package com.wjbjp.controller;

import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.EditPwdDto;
import com.wjbjp.model.dto.LogingDto;
import com.wjbjp.model.dto.ResetPasswordDto;
import com.wjbjp.model.entity.FaUserEntity;

public interface LoginController {
    ResultObj<FaUserEntity> userLogin(LogingDto inEnt);

    ResultObj<Integer> loginReg(LogingDto inEnt);

    Result resetPassword(ResetPasswordDto inEnt);

    Result deleteUser(String userName);

    ResultObj<Boolean> userEditPwd(EditPwdDto inEnt);

    Result userEditLoginName(String oldName, String newName);
}
