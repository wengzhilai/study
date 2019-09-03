package com.equipment.service.impl;

import com.equipment.model.entity.FaTableColumnEntity;
import com.equipment.model.entity.FaTableTypeEntity;
import com.equipment.service.MyBatisService;
import com.equipment.service.TableService;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.retention.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    MyBatisService<FaTableTypeEntity> moduleMhs;

    EntityHelper<FaTableTypeEntity> moduleEh = new EntityHelper<>(new FaTableTypeEntity());

    @Override
    public FaTableTypeEntity SingleByKey(int key) {
        return moduleMhs.getSingleByPrimaryKey(moduleEh, key);
    }

    @Override
    public ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt) {
        return null;
    }

    @Override
    public ResultObj<Integer> Delete(int key) {
        return null;
    }

    @Override
    public ResultObj<KVT> GetTableSelect() {
        return null;
    }

    @Override
    public String MakeSqlCreateTable(FaTableTypeEntity inEnt) {
        return null;
    }

    @Override
    public String MakeSqlAlterTable(String tableName, FaTableColumnEntity inEnt) {
        return null;
    }

    @Override
    public String MakeSqlAlterAddColumn(String tableName, FaTableColumnEntity inEnt) {
        return null;
    }

    @Override
    public String MakeSqlAlterChangeColumn(String tableName, String oldName, FaTableColumnEntity inEnt) {
        return null;
    }

    @Override
    public String MakeSqlAlterUniqueColumn(String tableName, FaTableColumnEntity inEnt) {
        return null;
    }
}
