package com.equipment.controller.impl;

import com.equipment.controller.TableController;
import com.equipment.model.entity.FaTableTypeEntity;
import com.equipment.service.TableService;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Table")
@RestController
public class TableControllerImpl implements TableController {
    @Autowired
    TableService ts;

    @RequestMapping(value = "/SingleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个")
    public FaTableTypeEntity SingleByKey(int key) {
        FaTableTypeEntity t=ts.SingleByKey(key);
        return t;
    }

    @RequestMapping(value = "/GetTableSelect", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有自定义表的列表")
    public ResultObj<KVT> GetTableSelect() {
        return ts.GetTableSelect();
    }
}
