package com.user.consumer.feign.impl;

import com.user.consumer.feign.RoleService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaRoleEntity;

public class RoleServiceImpl implements RoleService {
    @Override
    public ResultObj<FaRoleEntity> singleByKey(int key) {
        ResultObj<FaRoleEntity>  reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    @Override
    public Result delete(int key) {
        Result reObj=new Result();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaRoleEntity> inEnt) {
        ResultObj<Integer>  reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }
}
