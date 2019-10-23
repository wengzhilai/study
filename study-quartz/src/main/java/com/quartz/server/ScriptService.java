package com.quartz.server;


import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaScriptEntity;

import java.util.List;

public interface ScriptService {
    //region 基本方法
    /**
     * 查询单条
     * @param inEnt
     * @return
     */
    ResultObj<FaScriptEntity> singleByKey(DtoDo inEnt);

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
    ResultObj<Integer> save(DtoSave<FaScriptEntity> inEnt);
    //endregion

    /**
     * 获取正常的任务脚本,并配置了，runwhere
     * @return
     */
    List<FaScriptEntity> getNormalScript();

    //——代码分隔线——

}
