package com.wjbjp.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FaFlow implements Serializable {
    /**
    * 
    */
    private Integer id;

    /**
    * 
    */
    private String name;

    /**
    * 数据外导
            薪酬结果
            政策修改
    */
    private String flowType;

    /**
    * 
    */
    private String remark;

    /**
    * ID,
            X,
            Y
    */
    private String xY;

    /**
    * 
    */
    private String region;

    private static final long serialVersionUID = 1L;
}