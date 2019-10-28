package com.wzl.commons.model;

import lombok.Data;

import java.util.List;

@Data
public class KV {
    public KV(){

    }
    public KV(String _k,String _v){
        this.k=_k;
        this.v=_v;
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
     *
     */
    public List<KV> child;
}
