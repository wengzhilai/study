package com.equipment.consumer.feign.impl;

import com.equipment.consumer.feign.EquipmentService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import org.springframework.stereotype.Service;

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

    //——代码分隔线——

}
