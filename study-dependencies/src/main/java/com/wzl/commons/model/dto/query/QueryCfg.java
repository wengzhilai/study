package com.wzl.commons.model.dto.query;

import com.wzl.commons.retention.*;
import lombok.Data;

/**
 * 配置
 */
@Data
public class QueryCfg {
    /**
     * 字段名称
     */
    @Display(Name = "字段名称")
    public String fieldName;
    /**
     * 别名
     */
    @Display(Name = "别名")
    public String alias;
    /**
     * 是否可搜索
     */
    @Display(Name = "是否可搜索")
    public Boolean canSearch;

    /**
     * 过滤控件类型
     */
    @Display(Name = "过滤控件类型")
    public String searchType;

    /**
     * 过滤控件脚本
     */
    @Display(Name = "过滤控件脚本")
    public String searchScript;
    /**
     * 是否显示
     */
    @Display(Name = "是否显示")
    public Boolean show;
    /**
     * 宽度
     */
    @Display(Name = "宽度")
    public String width;
    /**
     * 可排序
     */
    @Display(Name = "可排序")
    public Boolean sortable;

    /**
     * 变量
     */
    @Display(Name = "变量")
    public String isVariable;

    /**
     * 字段类型
     */
    @Display(Name = "字段类型")
    public String fieldType;
    /**
     * 格式化
     */
    @Display(Name = "格式化")
    public String format;
}
