package com.user.consumer.controller.impl;


import com.user.consumer.controller.ModuleController;
import com.user.consumer.model.entity.FaModuleEntity;
import com.wzl.commons.model.ResultObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Login")
public class ModuleControllerImpl implements ModuleController {
    @Override
    public ResultObj<FaModuleEntity> getUserMenu() {
        return null;
    }
}
