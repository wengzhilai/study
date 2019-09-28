package com.user.consumer.controller.impl;

import com.user.consumer.controller.QueryController;
import com.user.consumer.feign.ModuleService;
import com.user.consumer.feign.QueryService;
import com.user.consumer.model.entity.FaQueryEntity;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("query")
public class QueryControllerImpl implements QueryController {

    @Autowired
    private QueryService service;
    @Override
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj);
    }
}
