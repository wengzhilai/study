package com.equipment.provider.controller.impl;

import com.equipment.provider.controller.EquipmentController;
import com.equipment.provider.server.EquipmentService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
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

    @ApiOperation(value="获取Equipment对象")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaEquipmentEntity> singleByKey(@RequestBody DtoDo inEnt) {
        return service.singleByKey(inEnt);
    }

    @ApiOperation(value="删除Equipment对象")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(inEnt);
    }

    @ApiOperation(value="删除Equipment对象")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaEquipmentEntity> inEnt) {
        return service.save(inEnt);
    }

    @ApiOperation(value="获取表的选择框")
    @RequestMapping(value = "getTree", method = RequestMethod.POST)
    public ResultObj<KV> getTree(@RequestBody DtoDo inEnt) {
        return service.getTree(inEnt);
    }

    @ApiOperation(value="获取设备配置")
    @RequestMapping(value = "getConfig", method = RequestMethod.POST)
    public ResultObj<SmartTableSetting> getConfig(@RequestBody DtoDo inEnt) {
        return service.getConfig(inEnt);
    }

    @ApiOperation(value="获取配置信息和数据")
    @RequestMapping(value = "getConfigAndData", method = RequestMethod.POST)
    public ResultObj<HashMap<String,Object>> getConfigAndData(@RequestBody QuerySearchDto inEnt) {
        return service.getConfigAndData(inEnt);
    }

    //——代码分隔线——
}