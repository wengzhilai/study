package com.user.consumer.controller;

import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.ResultObj;
import org.springframework.web.bind.annotation.RequestBody;

public interface ModuleController {

    /**
     * 查询单个
     * @param inEnt
     * @return
     */
    ResultObj<FaModuleEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 保存
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaModuleEntity> inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    Result delete(@RequestBody DtoDo inEnt);

    /**
     * 获取菜单
     * @return
     */
    ResultObj<FaModuleEntity> getUserMenu();

    //——代码分隔线——
}
