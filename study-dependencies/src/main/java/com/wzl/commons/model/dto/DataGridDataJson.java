package com.wzl.commons.model.dto;

import com.wzl.commons.model.entity.FaQueryEntity;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class DataGridDataJson {

    public FaQueryEntity Config;
    public int total;
    public List<HashMap<String,Object>> rows;
    public String errMsg;
}
