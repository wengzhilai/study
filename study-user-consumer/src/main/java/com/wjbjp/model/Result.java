package com.wjbjp.model;

import lombok.Data;

/**
 * 返回结果
 */
@Data
public class Result {
    public boolean success;
    public String msg;
    public String code;
}

