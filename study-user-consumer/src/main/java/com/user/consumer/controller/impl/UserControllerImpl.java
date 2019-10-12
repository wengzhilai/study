package com.user.consumer.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.consumer.controller.UserController;
import com.user.consumer.feign.UserService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaUserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "查询单个角色")
    public ResultObj<FaUserEntity> singleByKey(@RequestBody DtoDo inObj) {
        return service.singleByKey(inObj);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除角色")
    public Result delete(@RequestBody DtoDo inObj) {
        return service.delete(inObj);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "保存角色")
    public ResultObj<Integer> save(@RequestBody DtoSave<FaUserEntity> inEnt) {
        return service.save(inEnt);
    }

    //——代码分隔线——

}
