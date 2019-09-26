package com.user.consumer.controller.impl;


import cn.hutool.core.convert.Convert;
import com.user.consumer.controller.BaseController;
import com.user.consumer.controller.ModuleController;
import com.user.consumer.feign.LoginService;
import com.user.consumer.feign.ModuleService;
import com.user.consumer.model.entity.FaModuleEntity;
import com.user.consumer.model.entity.view.FaRoleModuleEntityView;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("module")
public class ModuleControllerImpl extends BaseController implements ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "getUserMenu", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户菜单")
    public ResultObj<FaModuleEntity> getUserMenu() {
        DtoDo postEnt=new DtoDo();
        postEnt.key=Convert.toStr(this.CurrUser.userId);
        return moduleService.getUserMenu(postEnt);
    }
}
