package com.equipment.consumer.feign;

import com.equipment.consumer.feign.impl.EquipmentServiceImpl;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-equipment",url = "http://localhost:9003",fallback = EquipmentServiceImpl.class)
public interface EquipmentService {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/equipment/singleByKey")
    ResultObj<FaEquipmentEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/equipment/delete")
    Result delete(@RequestBody DtoDo inEnt);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/equipment/save")
    ResultObj<Integer> save(@RequestBody DtoSave<FaEquipmentEntity> inEnt);
    //endregion

    /**
     * 获取表的选择框
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/equipment/getTree")
    ResultObj<KV> getTree(@RequestBody DtoDo inEnt);

    /**
     * 获取设备配置
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/equipment/getConfig")
    ResultObj<SmartTableSetting> getConfig(@RequestBody DtoDo inEnt);

    //——代码分隔线——
}