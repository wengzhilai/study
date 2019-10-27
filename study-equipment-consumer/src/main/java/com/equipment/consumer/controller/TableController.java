package com.equipment.consumer.controller;

import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaTableTypeEntity;

public interface TableController {
    /**
     *  获取所有自定义表的列表
     */
    ResultObj<KVT> GetTableSelect();


    /**
     * 获取表的单个对象
     */
    ResultObj<FaTableTypeEntity> SingleByKey(DtoDo key);

    /**
     * 保存和自定义Table
     * 包括列，对列只更新不删除，但可以禁用和户用
     * 保存成功后，生成创建脚本和修改脚本对物理表的列修改和添加
     */
    ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt);

    /**
     * 删除
     */
    ResultObj<Integer> Delete(DtoDo key);

}
