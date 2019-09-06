package com.wzl.commons.model;

import lombok.Data;

@Data
public class KVT {
    public KVT() {
    }

    public KVT(String k, String v, String t) {
        this.k = k;
        this.v = v;
        this.t = t;
    }

    public KVT(String k, String v) {
        this.k = k;
        this.v = v;
    }

    /**
     * 主键
     */
    public String k;

    /**
     * 值
     */
    public String v;
    /**
     * 类型
     */
    public String t;
}
