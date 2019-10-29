package com.equipment.consumer.controller;


import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface EquipmentController {
    //region 基本方法
    /**
     * 查询单条
     * @param inObj
     * @return
     */
    ResultObj<FaEquipmentEntity> singleByKey(@RequestBody DtoDo inObj);

    /**
     * 删除
     * @param inObj
     * @return
     */
    Result delete(@RequestBody DtoDo inObj);

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

    /**
     * 获取配置信息和数据
     * @param inEnt
     * @return
     */
    ResultObj<HashMap<String,Object>> getConfigAndData(@RequestBody QuerySearchDto inEnt);

    //——代码分隔线——
}