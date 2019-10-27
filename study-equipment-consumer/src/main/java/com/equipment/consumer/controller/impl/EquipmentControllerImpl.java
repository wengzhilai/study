package com.equipment.consumer.controller.impl;

import cn.hutool.core.convert.Convert;
import com.equipment.consumer.controller.EquipmentController;
import com.equipment.consumer.feign.EquipmentService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    //——代码分隔线——

}
