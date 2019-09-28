package com.user.provider.server;


import com.user.provider.model.entity.FaQueryEntity;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;

public interface QueryService {
    //region 基本方法
    /**
     * 查询单条
     * @param key
     * @return
     */
    FaQueryEntity singleByKey(int key);

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
    ResultObj<Integer> save(DtoSave<FaQueryEntity> inEnt);
    //endregion

    ResultObj<FaQueryEntity> GetSingleQuery(String code);
}
