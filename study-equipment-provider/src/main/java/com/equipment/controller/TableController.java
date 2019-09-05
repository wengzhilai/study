package com.equipment.controller;

import com.equipment.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;

public interface TableController {
    FaTableTypeEntity SingleByKey(int key);
    /**
     *  获取所有自定义表的列表
     */
    ResultObj<KVT> GetTableSelect();
}
