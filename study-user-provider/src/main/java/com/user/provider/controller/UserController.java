package com.user.provider.controller;


import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaUserEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    ResultObj<FaUserEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    Result delete(@RequestBody DtoDo inEnt);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaUserEntity> inEnt);
    //endregion

    //——代码分隔线——

}
