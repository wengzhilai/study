package com.wzl.commons.model;

import lombok.Data;

@Data
public class DtoDo {
    public DtoDo() {
    }

    public DtoDo(String key) {
        this.key = key;
    }

    public String key;

}
