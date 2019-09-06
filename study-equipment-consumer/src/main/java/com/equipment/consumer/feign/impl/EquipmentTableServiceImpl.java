package com.equipment.consumer.feign.impl;

import com.equipment.consumer.feign.EquipmentTableService;
import com.equipment.consumer.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import org.springframework.stereotype.Component;

@Component
public class EquipmentTableServiceImpl implements EquipmentTableService {
    @Override
    public ResultObj<KVT> GetTableSelect() {
        return new ResultObj<>(false,"网络有问题1");
    }

    @Override
    public ResultObj<FaTableTypeEntity> SingleByKey(DtoDo key) {
        return new ResultObj<>(false,"网络有问题1");
    }

    @Override
    public ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt) {
        return new ResultObj<>(false,"网络有问题1");
    }

    @Override
    public ResultObj<Integer> Delete(DtoDo key) {
        return new ResultObj<>(false,"网络有问题1");
    }
}
