package com.user.consumer.controller.impl;


import cn.hutool.core.convert.Convert;
import com.user.consumer.controller.BaseController;
import com.user.consumer.controller.ModuleController;
import com.user.consumer.feign.LoginService;
import com.user.consumer.feign.ModuleService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.dto.DtoSave;
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
@RequestMapping("module")
public class ModuleControllerImpl extends BaseController implements ModuleController {

    @Autowired
    private ModuleService service;


    @RequestMapping(value = "getUserMenu", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户菜单")
    public ResultObj<FaModuleEntity> getUserMenu() {
        DtoDo postEnt=new DtoDo();
        postEnt.key=Convert.toStr(this.CurrUser.userId);
        return service.getUserMenu(postEnt);
    }

    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "查询单个")
    public ResultObj<FaModuleEntity> singleByKey(@RequestBody DtoDo inEnt) {
        return service.singleByKey(inEnt);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    public ResultObj<Integer> save(@RequestBody DtoSave<FaModuleEntity> inEnt) {
        return service.save(inEnt);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(inEnt);
    }


    //——代码分隔线——
}
