package com.user.provider.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.provider.controller.ModuleController;

import com.user.provider.model.entity.FaModuleEntity;
import com.user.provider.model.entity.view.FaRoleModuleEntityView;
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
@RequestMapping("Module")
public class ModuleControllerImpl implements ModuleController {

    @Autowired
    ModuleService moduleService;

    @ApiOperation(value="获取用户所有模块")
    @RequestMapping(value = "getUserMenu", method = RequestMethod.POST)
    public ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt) {
        return moduleService.GetMGetMenuByUserId(Convert.toInt(inEnt.key));
    }
}
