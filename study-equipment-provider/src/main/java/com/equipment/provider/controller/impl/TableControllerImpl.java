package com.equipment.provider.controller.impl;

import com.equipment.provider.controller.TableController;
import com.equipment.provider.server.TableService;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaTableTypeEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("Table")
@RestController
public class TableControllerImpl implements TableController {
    @Autowired
    TableService ts;

    @RequestMapping(value = "/GetTableSelect", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有自定义表的列表")
    public ResultObj<KVT> GetTableSelect() {
        return ts.GetTableSelect();
    }


    @RequestMapping(value = "/SingleByKey", method = RequestMethod.POST,consumes = "application/json")
    @ApiOperation(value = "获取单个")
    public ResultObj<FaTableTypeEntity> SingleByKey(@RequestBody DtoDo inObj) {
        ResultObj<FaTableTypeEntity> t=ts.SingleByKey(Integer.valueOf(inObj.key));
        return t;
    }

    @RequestMapping(value = "/Save", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    public ResultObj<Integer> Save(@RequestBody DtoSave<FaTableTypeEntity> inEnt) {
        return ts.Save(inEnt);
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除")
    public ResultObj<Integer> Delete(@RequestBody DtoDo inObj) {
        return ts.Delete(Integer.valueOf(inObj.key));
    }


}
