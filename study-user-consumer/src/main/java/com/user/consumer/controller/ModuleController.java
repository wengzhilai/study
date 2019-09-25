package com.user.consumer.controller;

import com.user.consumer.model.entity.FaModuleEntity;
import com.wzl.commons.model.ResultObj;

public interface ModuleController {
    ResultObj<FaModuleEntity> getUserMenu();

}
