package com.wzl.commons.model.dto;

import com.wzl.commons.model.entity.FaQueryEntity;
import lombok.Data;

@Data
public class DataGridDataJson {

    public FaQueryEntity Config;
    public int total;
    public Object rows;
    public String errMsg;
}
