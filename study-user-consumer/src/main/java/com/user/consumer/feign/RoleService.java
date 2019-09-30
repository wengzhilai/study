package com.user.consumer.feign;


import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaRoleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider-role",url = "http://localhost:9001")
public interface RoleService {
    //region 基本方法
    /**
     * 查询单条
     * @param key
     * @return
     */
    @GetMapping(value = "/role/singleByKey")
    ResultObj<FaRoleEntity> singleByKey(@RequestBody int key);

    /**
     * 删除
     * @param key
     * @return
     */
    @GetMapping(value = "/role/delete")
    Result delete(@RequestBody int key);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    @GetMapping(value = "/role/save")
    ResultObj<Integer> save(@RequestBody DtoSave<FaRoleEntity> inEnt);
    //endregion


}
