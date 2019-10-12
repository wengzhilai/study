package com.user.consumer.feign.impl;

import com.user.consumer.feign.UserService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaUserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResultObj<FaUserEntity> singleByKey(DtoDo inEnt) {
        ResultObj<FaUserEntity>  reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    @Override
    public Result delete(DtoDo inEnt) {
        Result reObj=new Result();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaUserEntity> inEnt) {
        ResultObj<Integer>  reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    //——代码分隔线——

}
