package com.user.provider.controller.impl;

import com.user.provider.controller.QueryController;
import com.user.provider.model.entity.FaQueryEntity;
import com.user.provider.server.ModuleService;
import com.user.provider.server.QueryService;
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
    QueryService service;

    @ApiOperation(value="获取Query所有内容")
    @RequestMapping(value = "GetSingleQuery", method = RequestMethod.POST)
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj.key);
    }
}
