package com.user.provider.controller;


import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaRoleEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleController {
    //region 基本方法
    /**
     * 查询单条
     * @param key
     * @return
     */
    ResultObj<FaRoleEntity> singleByKey(@RequestBody int key);

    /**
     * 删除
     * @param key
     * @return
     */
    Result delete(@RequestBody int key);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaRoleEntity> inEnt);
    //endregion


}
