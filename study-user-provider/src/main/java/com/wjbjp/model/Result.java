package com.wjbjp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

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

