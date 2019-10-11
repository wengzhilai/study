package com.user.consumer.feign;

import com.user.consumer.feign.impl.LoginServiceImpl;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-model",url = "http://localhost:9001")
public interface ModuleService {

    /**
     * 查询单个Query
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/module/singleByKey")
    ResultObj<FaModuleEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 保存Query
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/module/save")
    ResultObj<Integer> save(@RequestBody DtoSave<FaModuleEntity> inEnt);

    /**
     * 删除Query
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/module/delete")
    Result delete(@RequestBody DtoDo inEnt);



    /**
     * 获取用户的所有菜单
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/module/getUserMenu")
    ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt);

    //——代码分隔线——
}
