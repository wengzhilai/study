package com.equipment.consumer.feign.impl;

import com.equipment.consumer.feign.EquipmentService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.EquipmentDto;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Override
    public ResultObj<FaEquipmentEntity> singleByKey(DtoDo inEnt) {
        ResultObj<FaEquipmentEntity>  reObj=new ResultObj<> ();
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
    public ResultObj<Integer> save(DtoSave<FaEquipmentEntity> inEnt) {
        ResultObj<Integer>  reObj=new ResultObj<> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<KV> getTree(DtoDo inEnt) {
        ResultObj<KV> reObj=new ResultObj<KV> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<SmartTableSetting> getConfig(DtoDo inEnt) {
        ResultObj<SmartTableSetting> reObj=new ResultObj<SmartTableSetting> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<HashMap<String,Object>> getConfigAndData(QuerySearchDto inEnt) {
        ResultObj<HashMap<String,Object>> reObj=new ResultObj<HashMap<String,Object>> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<Integer> saveEquiment(EquipmentDto inEnt) {
        ResultObj<Integer> reObj=new ResultObj<Integer> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    public ResultObj<Integer> deleteEquiment(EquipmentDto inEnt) {
        ResultObj<Integer> reObj=new ResultObj<Integer> ();
        reObj.success=false;
        reObj.msg="网络有问题";
        return reObj;
    }

    //——代码分隔线——
}