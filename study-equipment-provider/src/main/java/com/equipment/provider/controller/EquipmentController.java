package com.equipment.provider.controller;


import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface EquipmentController {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    ResultObj<FaEquipmentEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    Result delete(@RequestBody DtoDo inEnt);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaEquipmentEntity> inEnt);
    //endregion



    /**
     * 获取表的选择框
     * @param inEnt
     * @return
     */
    ResultObj<KV> getTree(@RequestBody DtoDo inEnt);

    /**
     * 获取设备配置
     * @param inEnt
     * @return
     */
    ResultObj<SmartTableSetting> getConfig(@RequestBody DtoDo inEnt);

    //——代码分隔线——
}