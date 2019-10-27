package com.equipment.consumer.controller.impl;

import com.equipment.consumer.controller.TableController;
import com.equipment.consumer.feign.EquipmentTableService;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaTableTypeEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("table")
public class TableControllerImpl implements TableController {


    @Autowired
    private EquipmentTableService testC;

    @RequestMapping(value = "/GetTableSelect", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有自定义表的列表")
    public ResultObj<KVT> GetTableSelect() {
        return testC.GetTableSelect();
    }



    @RequestMapping(value = "/singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "获取单个")
    public ResultObj<FaTableTypeEntity> SingleByKey(@RequestBody DtoDo inEnt) {
        return testC.SingleByKey(inEnt);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    public ResultObj<Integer> Save(@RequestBody DtoSave<FaTableTypeEntity> inEnt) {
        return testC.Save(inEnt);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除")
    public ResultObj<Integer> Delete(@RequestBody DtoDo inEnt) {
        return testC.Delete(inEnt);
    }
}
