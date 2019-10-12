package com.user.provider.server;


import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.FaQueryEntity;
import com.wzl.commons.model.entity.FaUserEntity;

public interface UserService {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    ResultObj<FaUserEntity> singleByKey(DtoDo inEnt);

    /**
     * 删除
     * @param inEnt
     * @return
     */
    Result delete(DtoDo inEnt);

    /**
     * 保存基本信息
     * @param inEnt
     * @return
     */
    ResultObj<Integer> save(DtoSave<FaUserEntity> inEnt);
    //endregion

    //——代码分隔线——

}
