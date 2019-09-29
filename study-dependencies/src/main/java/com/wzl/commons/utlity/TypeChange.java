package com.wzl.commons.utlity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 类型转换
 */
public class TypeChange {
    /**
     * 对象转字符串
     *
     * @param obj
     * @return
     */
    public static String objToString(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 字符串转JSONObject
     *
     * @param inStr
     * @return
     */
    public static JSONObject jsonStrToJsonObject(String inStr) {
        return JSON.parseObject(inStr);
    }

    /**
     * 字符串转JavaBean
     *
     * @param inStr
     * @param _classType
     * @param <T>
     * @return
     */
    public static <T> T jsonStrToJavaBean(String inStr, Class<T> _classType) {
        return JSON.parseObject(inStr, _classType);
    }

    /**
     * 字符串转JavaBean列表
     * @param inStr
     * @param _classType
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStrToJavaBeanList(String inStr, Class<T> _classType) {
        return JSON.parseArray(inStr, _classType);
    }
}
