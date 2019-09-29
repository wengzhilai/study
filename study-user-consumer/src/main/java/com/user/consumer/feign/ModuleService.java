package com.user.consumer.feign;

import com.user.consumer.feign.impl.LoginServiceImpl;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-model",url = "http://localhost:9001")
public interface ModuleService {
    /**
     * 获取用户的所有菜单
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/Module/getUserMenu")
    ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt);

}
