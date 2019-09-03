package com.wjbjp.model.dto;

import java.util.List;

/**
 * 保存和修改实体
 * @param <T>
 */
public class DtoSave<T> {
    /**
    * 用于操作的对象
    */
    public T data ;
    /**
    * 需要保存的字段
    */
    public List<String> saveFieldList ;
    /**
    * 需要忽略的字段
    */
    public List<String> ignoreFieldList ;
    /**
    * 更新条件,如果为空.则以主键为更新条件
    */
    public List<String> whereList ;
}
