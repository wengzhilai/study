package com.user.provider.server;


import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;

import java.util.List;

public interface ModuleService {
    /**
     * 查询单条
     * @param key
     * @return
     */
    ResultObj<FaModuleEntity> singleByKey(int key);

    /**
     * 删除
     * @param key
     * @return
     */
    Result delete(int key);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(DtoSave<FaModuleEntity> inEnt);


    /**
     * 查找所有模块菜单
     * @return
     */
    ResultObj<FaModuleEntity> getMenu();

    /**
     *
     * @param roleIdList
     * @return
     */
    ResultObj<FaModuleEntity> getMenuByRoleId(List<Integer> roleIdList);

    /**
     * 获取用户的所有模块
     * @param userId
     * @return
     */
    ResultObj<FaModuleEntity> GetMGetMenuByUserId(int userId);

    //——代码分隔线——

}
