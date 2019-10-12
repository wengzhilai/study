package com.user.provider.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.provider.controller.QueryController;
import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.user.provider.server.ModuleService;
import com.user.provider.server.QueryService;
import com.wzl.commons.model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("query")
public class QueryControllerImpl implements QueryController {

    @Autowired
    QueryService service;

    @ApiOperation(value="获取Query所有内容")
    @RequestMapping(value = "GetSingleQuery", method = RequestMethod.POST)
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj.key);
    }

    @ApiOperation(value="获取Query所有数据")
    @RequestMapping(value = "getListData", method = RequestMethod.POST)
    public ResultObj<DataGridDataJson> getListData(@RequestBody QuerySearchDto inObj) {
        ResultObj<DataGridDataJson> resultObj=service.getListData(inObj);
        return resultObj;
    }

    @ApiOperation(value="查询单个Query")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaQueryEntity> singleByKey(@RequestBody int inEnt) {
        return service.singleByKey(inEnt);
    }

    @ApiOperation(value="保存Query")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaQueryEntity> inEnt) {
        return service.save(inEnt);
    }

    @ApiOperation(value="删除Query")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(Convert.toInt(inEnt.key));
    }


    //——代码分隔线——
}