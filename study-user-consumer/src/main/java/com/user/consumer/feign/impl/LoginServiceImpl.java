package com.user.consumer.feign.impl;

import com.user.consumer.feign.LoginService;
import com.user.consumer.model.entity.FaUserEntity;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public ResultObj<Integer> loginReg(LogingDto inEnt) {
        return null;
    }

    @Override
    public ResultObj<FaUserEntity> userLogin(LogingDto inEnt) {
        return null;
    }

    @Override
    public ResultObj<FaUserEntity> getSingleByPwd(LogingDto inEnt) {
        return null;
    }

    @Override
    public Result resetPassword(ResetPasswordDto inEnt) {
        return null;
    }

    @Override
    public ResultObj<Boolean> userEditPwd(EditPwdDto inEnt) {
        return null;
    }

    @Override
    public Result userEditUser(DtoSave<FaUserEntity> inDto) {
        return null;
    }
}
