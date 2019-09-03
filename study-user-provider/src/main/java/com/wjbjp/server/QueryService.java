package com.wjbjp.server;

import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.DtoSave;
import com.wjbjp.model.entity.FaQueryEntity;

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

}
