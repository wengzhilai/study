package com.equipment.consumer.controller.impl;

import cn.hutool.core.convert.Convert;
import com.equipment.consumer.controller.EquipmentController;
import com.equipment.consumer.feign.EquipmentService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.EquipmentDto;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("equipment")
public class EquipmentControllerImpl implements EquipmentController {

    @Autowired
    EquipmentService service;

    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "查询单个角色")
    public ResultObj<FaEquipmentEntity> singleByKey(@RequestBody DtoDo inObj) {
        return service.singleByKey(inObj);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除角色")
    public Result delete(@RequestBody DtoDo inObj) {
        return service.delete(inObj);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "保存角色")
    public ResultObj<Integer> save(@RequestBody DtoSave<FaEquipmentEntity> inEnt) {
        return service.save(inEnt);
    }

    @RequestMapping(value = "getTree", method = RequestMethod.POST)
    @ApiOperation(value = "获取表的选择框")
    public ResultObj<KV> getTree(@RequestBody DtoDo inEnt) {
        return service.getTree(inEnt);
    }

    @RequestMapping(value = "getConfig", method = RequestMethod.POST)
    @ApiOperation(value = "获取设备配置")
    public ResultObj<SmartTableSetting> getConfig(@RequestBody DtoDo inEnt) {
        return service.getConfig(inEnt);
    }

    @RequestMapping(value = "getConfigAndData", method = RequestMethod.POST)
    @ApiOperation(value = "获取配置信息和数据")
    public ResultObj<HashMap<String,Object>> getConfigAndData(@RequestBody QuerySearchDto inEnt) {
        return service.getConfigAndData(inEnt);
    }

    @RequestMapping(value = "saveEquiment", method = RequestMethod.POST)
    @ApiOperation(value = "保存设备")
    public ResultObj<Integer> saveEquiment(@RequestBody EquipmentDto inEnt) {
        return service.saveEquiment(inEnt);
    }

    @RequestMapping(value = "deleteEquiment", method = RequestMethod.POST)
    @ApiOperation(value = "删除设备")
    public ResultObj<Integer> deleteEquiment(@RequestBody EquipmentDto inEnt) {
        return service.deleteEquiment(inEnt);
    }

    //——代码分隔线——
}