package com.user.consumer.feign;


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
}
