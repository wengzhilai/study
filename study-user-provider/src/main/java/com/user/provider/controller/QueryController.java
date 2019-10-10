package com.user.provider.controller;

import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

public interface QueryController {
    /**
     * 根据代码查询Query
     * @param inObj
     * @return
     */
    ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj);

    /**
     * 获取数据
     * @param querySearchModel
     * @return
     */
    ResultObj<DataGridDataJson> getListData(@RequestBody QuerySearchDto querySearchModel);

    /**
     * 查询单个Query
     * @param inEnt
     * @return
     */
    ResultObj<FaQueryEntity> singleByKey(@RequestBody int inEnt);

    /**
     * 保存Query
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(@RequestBody DtoSave<FaQueryEntity> inEnt);

    /**
     * 删除Query
     * @param inEnt
     * @return
     */
    Result delete(@RequestBody DtoDo inEnt);


    //——代码分隔线——
}