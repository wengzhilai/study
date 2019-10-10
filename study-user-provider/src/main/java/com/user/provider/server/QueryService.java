package com.user.provider.server;


import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
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
    ResultObj<FaQueryEntity> singleByKey(int key);

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

    /**
     * 根据代码查询配置
     * @param code
     * @return
     */
    ResultObj<FaQueryEntity> GetSingleQuery(String code);

    /**
     * 获取数据
     * @param inObj
     * @return
     */
    ResultObj<DataGridDataJson> getListData(QuerySearchDto inObj);

    //——代码分隔线——
}