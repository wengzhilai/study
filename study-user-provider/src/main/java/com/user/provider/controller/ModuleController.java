package com.user.provider.controller;

import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.web.bind.annotation.RequestBody;

public interface ModuleController {
    /**
     * 获取用户的所有菜单
     * @param inEnt
     * @return
     */
    ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt);

}
