package com.user.provider.controller;

import com.wzl.commons.model.Result;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.web.bind.annotation.RequestBody;

public interface ModuleController {


    /**
     * 查询单个Query
     * @param inEnt
     * @return
     */
    ResultObj<FaModuleEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 保存Query
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaModuleEntity> inEnt);

    /**
     * 删除Query
     * @param inEnt
     * @return
     */
    Result delete(@RequestBody DtoDo inEnt);


    /**
     * 获取用户的所有菜单
     * @param inEnt
     * @return
     */
    ResultObj<FaModuleEntity> getUserMenu(@RequestBody DtoDo inEnt);

    //——代码分隔线——
}
