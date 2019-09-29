package com.user.consumer.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.consumer.controller.RoleController;
import com.user.consumer.feign.RoleService;
import com.wzl.commons.model.DtoDo;
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

    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "查询单个角色")
    public FaRoleEntity singleByKey(@RequestBody DtoDo inObj) {
        return service.singleByKey(Convert.toInt(inObj.key));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除角色")
    public Result delete(DtoDo inObj) {
        return service.delete(Convert.toInt(inObj.key));
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "保存角色")
    public ResultObj<Integer> save(DtoSave<FaRoleEntity> inEnt) {
        return service.save(inEnt);
    }
}
