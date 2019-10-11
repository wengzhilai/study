package com.user.provider.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.provider.controller.ModuleController;

import com.wzl.commons.model.Result;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.*;
import com.user.provider.server.LoginService;
import com.user.provider.server.ModuleService;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("module")
public class ModuleControllerImpl implements ModuleController {

    @Autowired
    ModuleService service;

    @ApiOperation(value="获取Role对象")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaModuleEntity> singleByKey(@RequestBody DtoDo inEnt) {
        return service.singleByKey(Convert.toInt(inEnt.key));
    }

    @ApiOperation(value="删除Role对象")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(Convert.toInt(inEnt.key));
    }

    @ApiOperation(value="删除Role对象")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaModuleEntity> inEnt) {
        return service.save(inEnt);
    }



    @ApiOperation(value="获取用户所有模块")
    @RequestMapping(value = "getUserMenu", method = RequestMethod.POST)
    public ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt) {
        return service.GetMGetMenuByUserId(Convert.toInt(inEnt.key));
    }
}
