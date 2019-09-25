package com.user.provider.controller;

import com.user.provider.model.entity.FaModuleEntity;
import com.user.provider.model.entity.view.FaRoleModuleEntityView;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;

public interface ModuleController {
    /**
     * 获取用户的所有菜单
     * @param inEnt
     * @return
     */
    ResultObj<FaRoleModuleEntityView> getUserMenu(DtoDo inEnt);

}
