package com.wjbjp.server;

import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.DtoSave;
import com.wjbjp.model.entity.FaModuleEntity;
import java.util.List;

public interface ModuleService {
    /**
     * 查询单条
     * @param key
     * @return
     */
    FaModuleEntity singleByKey(int key);

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

}
