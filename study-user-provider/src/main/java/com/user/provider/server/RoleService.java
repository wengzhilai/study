package com.user.provider.server;


import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.FaQueryEntity;
import com.wzl.commons.model.entity.FaRoleEntity;

public interface RoleService {
    //region 基本方法
    /**
     * 查询单条
     * @param key
     * @return
     */
    ResultObj<FaRoleEntity> singleByKey(int key);

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
    ResultObj<Integer> save(DtoSave<FaRoleEntity> inEnt);
    //endregion


}
