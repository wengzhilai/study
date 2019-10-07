package com.user.consumer.feign;


import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-uery",url = "http://localhost:9001")
public interface QueryService {

    @GetMapping(value = "/query/GetSingleQuery")
    ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo code);

    /**
     * 获取数据
     * @param querySearchModel
     * @return
     */
    @GetMapping(value = "/query/getListData")
    ResultObj<DataGridDataJson> getListData(@RequestBody QuerySearchDto querySearchModel);

    /**
     * 查询单个Query
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/query/singleByKey")
    ResultObj<FaQueryEntity> singleByKey(@RequestBody int inEnt);

    /**
     * 保存Query
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/query/save")
    ResultObj<Integer> save(@RequestBody DtoSave<FaQueryEntity> inEnt);

    //——代码分隔线——
}