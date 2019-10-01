package com.user.consumer.controller.impl;

import com.user.consumer.controller.QueryController;
import com.user.consumer.feign.ModuleService;
import com.user.consumer.feign.QueryService;
import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
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
    private QueryService service;

    @RequestMapping(value = "GetSingleQuery", method = RequestMethod.POST)
    @ApiOperation(value = "根据代码查询配置")
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj);
    }

    @RequestMapping(value = "getListData", method = RequestMethod.POST)
    @ApiOperation(value = "获取数据")
    public ResultObj<DataGridDataJson> getListData(QuerySearchDto inObj) {
        return service.getListData(inObj);
    }
}