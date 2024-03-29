package com.user.consumer.controller;

import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.*;
import com.wzl.commons.model.ResultObj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface QueryController {
    /**
     * 根据代码查询Query
     * @param inObj
     * @return
     */
    ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj);

    ResultObj<HashMap<String,Object>> getListData(@RequestBody QuerySearchDto querySearchModel);

    /**
     * 查询单个Query
     * @param inEnt
     * @return
     */
    ResultObj<FaQueryEntity> singleByKey(@RequestBody DtoDo inEnt);

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

    /**
     * 下载文件
     * @param postJson
     * @return
     */
    ResponseEntity<byte[]> downFile(String postJson);

    //——代码分隔线——
}