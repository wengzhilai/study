package com.wzl.commons.model;

import lombok.Data;

import java.util.List;

@Data
public class ResultObj<T>  extends Result{

    public ResultObj() {
        this.success = false;
    }

    public ResultObj(boolean success) {
        this.success = success;
    }

    public ResultObj(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public T data;

    public List<T> dataList;
}
