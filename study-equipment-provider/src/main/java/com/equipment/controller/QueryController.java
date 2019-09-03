package com.equipment.controller;

import com.wzl.commons.model.ResultObj;

import java.util.HashMap;

public interface QueryController {
    /**
     * 查询所有数据
     * @param sql
     * @return
     */
    ResultObj<HashMap<String,Object>> Query(String sql);
}
