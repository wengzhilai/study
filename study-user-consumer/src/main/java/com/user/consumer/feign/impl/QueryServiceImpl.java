package com.user.consumer.feign.impl;

import com.user.consumer.feign.QueryService;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.FaQueryEntity;
import feign.Response;

import java.util.HashMap;

public class QueryServiceImpl implements QueryService {
    @Override
    public ResultObj<FaQueryEntity> GetSingleQuery(DtoDo code) {
        return null;
    }

    @Override
    public ResultObj<HashMap<String,Object>> getListData(QuerySearchDto querySearchModel) {
        return null;
    }

    public ResultObj<FaQueryEntity> singleByKey(int inEnt) {
        ResultObj<FaQueryEntity> reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<Integer> save(DtoSave<FaQueryEntity> inEnt) {
        ResultObj<Integer> reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public Result delete(DtoDo inEnt) {
        Result reObj=new Result ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }


    public Response downFile(String postJson) {
        return null;
    }

    //——代码分隔线——
}