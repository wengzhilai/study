package com.wzl.commons.model.dto;

import java.util.List;

/**
 * 保存和修改实体
 * @param <T>
 */
public class DtoSave<T> {
    public DtoSave(){

    }
    public DtoSave(T data, List<String> saveFieldList, List<String> ignoreFieldList, List<String> whereList) {
        this.data = data;
        this.saveFieldList = saveFieldList;
        this.ignoreFieldList = ignoreFieldList;
        this.whereList = whereList;
    }

    public DtoSave(T data) {
        this.data = data;
    }

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
