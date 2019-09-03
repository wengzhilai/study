package com.wzl.commons.model;

import lombok.Data;

/**
 * 返回结果
 */
@Data
public class Result {

    public boolean success;
    public String msg;
    public String code;

    public Result() {
        this.success = false;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}

