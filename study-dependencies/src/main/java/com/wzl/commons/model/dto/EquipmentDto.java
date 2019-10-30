package com.wzl.commons.model.dto;

import lombok.Data;

@Data
public class EquipmentDto {
    /**
     * 传入的值
     */
    public int typeId ;

    /**
     * 主键
     */
    public int id ;

    /**
     * 数据内容
     */
    public String dataStr ;
}
