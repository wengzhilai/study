package com.user.consumer.controller;

import com.user.consumer.model.entity.FaModuleEntity;
import com.user.consumer.model.entity.view.FaRoleModuleEntityView;
import com.wzl.commons.model.ResultObj;

public interface ModuleController {
    ResultObj<FaModuleEntity> getUserMenu();

}
