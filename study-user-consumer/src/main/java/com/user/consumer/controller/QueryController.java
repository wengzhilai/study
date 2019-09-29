package com.user.consumer.controller;

import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.ResultObj;
import org.springframework.web.bind.annotation.RequestBody;

public interface QueryController {
    /**
     * 根据代码查询Query
     * @param inObj
     * @return
     */
    ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj);

    ResultObj<DataGridDataJson> getListData(@RequestBody QuerySearchDto querySearchModel);

}
