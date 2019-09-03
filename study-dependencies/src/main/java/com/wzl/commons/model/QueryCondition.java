package com.wzl.commons.model;

import lombok.Data;

@Data
public class QueryCondition {
    /**
    * 对象字段
    */
    public String objFiled ;
    /**
    * 操作符 如：between、in、《、》、=、like
    */
    public String opType ;
    /**
    * 值
    */
    public String value ;
    /**
    * 字段类型
    */
    public String fieldType ;
    /**
    * 字段名称
    */
    public String fieldName ;
}
