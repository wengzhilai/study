package com.quartz.controller.impl;

import cn.hutool.core.convert.Convert;
import com.quartz.controller.ScriptController;
import com.quartz.server.ScriptService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaScriptEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("script")
public class ScriptControllerImpl implements ScriptController {
    @Autowired
    ScriptService service;

    @ApiOperation(value="获取Script对象")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaScriptEntity> singleByKey(@RequestBody DtoDo inEnt) {
        ResultObj<FaScriptEntity> resultObj=new ResultObj<>(true);
        resultObj.data= service.singleByKey(Convert.toInt(inEnt.key));
        return resultObj;
    }

    @ApiOperation(value="删除Script对象")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(inEnt);
    }

    @ApiOperation(value="删除Script对象")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaScriptEntity> inEnt) {
        return service.save(inEnt);
    }

    //——代码分隔线——

}
