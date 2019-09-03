package com.wjbjp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class WhereObj {
    List<WhereObj> must;
    List<WhereObj> should;
    List<String> itemList;
}
