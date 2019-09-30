package com.user.provider.controller.impl;

import com.user.provider.controller.RoleController;
import com.user.provider.server.RoleService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaRoleEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleControllerImpl implements RoleController {
    @Autowired
    RoleService service;

    @ApiOperation(value="获取Role对象")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaRoleEntity> singleByKey(@RequestBody int key) {
        return service.singleByKey(key);
    }

    @ApiOperation(value="删除Role对象")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody int key) {
        return service.delete(key);
    }

    @ApiOperation(value="删除Role对象")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaRoleEntity> inEnt) {
        return service.save(inEnt);
    }
}
