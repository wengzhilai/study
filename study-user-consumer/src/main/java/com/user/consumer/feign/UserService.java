package com.user.consumer.feign;

import com.user.consumer.feign.impl.UserServiceImpl;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-user",url = "http://localhost:9001",fallback = UserServiceImpl.class)
public interface UserService {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/user/singleByKey")
    ResultObj<FaUserEntity> singleByKey(@RequestBody DtoDo inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/user/delete")
    Result delete(@RequestBody DtoDo inEnt);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/user/save")
    ResultObj<Integer> save(@RequestBody DtoSave<FaUserEntity> inEnt);
    //endregion

    //——代码分隔线——

}
