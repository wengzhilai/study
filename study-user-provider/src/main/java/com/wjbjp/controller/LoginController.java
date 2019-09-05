package com.wjbjp.controller;


import com.wjbjp.model.entity.FaUserEntity;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.*;

public interface LoginController {
    ResultObj<FaUserEntity> userLogin(LogingDto inEnt);

    ResultObj<Integer> loginReg(LogingDto inEnt);

    Result resetPassword(ResetPasswordDto inEnt);

    Result deleteUser(String userName);

    ResultObj<Boolean> userEditPwd(EditPwdDto inEnt);

    Result userEditLoginName(String oldName, String newName);
}
