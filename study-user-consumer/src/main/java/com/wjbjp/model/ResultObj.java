package com.wjbjp.model;

import lombok.Data;

@Data
public class ResultObj<T>  extends Result{
    public T data;
}
