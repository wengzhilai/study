package com.wzl.commons.model.dto.query;
import com.wzl.commons.retention.*;
import lombok.Data;

@Data
public class QueryRowBtnShowCondition {
    /**
    * 对象字段
    */
    @Display(Name = "对象字段")
    public String objFiled;
    /**
    * 操作符 如：between、in、《、》、=、like
    */
    @Display(Name = "操作符")
    public String opType;
    /**
    * 值
    */
    @Display(Name = "值")
    public String value;
    /**
    * 字段类型
    */
    @Display(Name = "字段类型")
    public String fieldType;
    /**
    * 字段名称
    */
    @Display(Name = "字段名称")
    public String fieldName;
}
