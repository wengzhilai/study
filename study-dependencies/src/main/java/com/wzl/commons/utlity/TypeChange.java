package com.wzl.commons.utlity;

import com.alibaba.fastjson.JSON;

/**
 * 类型转换
 */
public class TypeChange {
    /**
     * 对象转字符串
     * @param obj
     * @return
     */
    public static String objToString(Object obj){
        return JSON.toJSONString(obj);
    }
}
