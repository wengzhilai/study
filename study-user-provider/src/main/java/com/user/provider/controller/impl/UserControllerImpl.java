package com.user.provider.controller.impl;

import com.user.provider.controller.UserController;
import com.user.provider.server.UserService;
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

    @ApiOperation(value="获取User对象")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaUserEntity> singleByKey(@RequestBody DtoDo inEnt) {
        return service.singleByKey(inEnt);
    }

    @ApiOperation(value="删除User对象")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(inEnt);
    }

    @ApiOperation(value="删除User对象")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaUserEntity> inEnt) {
        return service.save(inEnt);
    }

    //——代码分隔线——

}
