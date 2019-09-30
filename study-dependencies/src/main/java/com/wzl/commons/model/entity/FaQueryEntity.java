package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

/**
* 查询
*/
@Table("fa_query")
public class FaQueryEntity {

    /**
    * ID
    */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;
    /**
    * 名称
    */
    @StringLength(50)
    @Display(Name = "名称")
    @Column("NAME")
    public String name;
    /**
    * 代码
    */
    @StringLength(20)
    @Display(Name = "代码")
    @Column("CODE")
    public String code;
    /**
    * 自动加载
    */
    @Display(Name = "自动加载")
    @Column("AUTO_LOAD")
    public boolean autoLoad;
    /**
    * 页面大小
    */
    @Display(Name = "页面大小")
    @Column("PAGE_SIZE")
    public int pageSize;
    /**
    * 复选框
    */
    @Display(Name = "复选框")
    @Column("SHOW_CHECKBOX")
    public boolean showCheckbox;

    /**
    * 调试
    */
    @Display(Name = "调试")
    @Column("IS_DEBUG")
    public boolean isDebug;

    /**
    * 查询语句
    */
    @Display(Name = "查询语句")
    @Column("QUERY_CONF")

    public String queryConf;
    /**
    * 配置信息
    */
    @Display(Name = "配置信息")
    @Column("QUERY_CFG_JSON")
    public String queryCfgJson;
    /**
    * 传入参数
    */
    @Display(Name = "传入参数")
    @Column("IN_PARA_JSON")
    public String inParaJson;
    /**
    * JS脚本
    */
    @Display(Name = "JS脚本")
    @Column("JS_STR")
    public String jsStr;
    /**
    * 行按钮
    */
    @Display(Name = "行按钮")

    @Column("ROWS_BTN")
    public String rowsBtn;
    /**
    * 表头按钮
    */
    @Display(Name = "表头按钮")
    @Column("HEARD_BTN")
    public String heardBtn;
    /**
    * 报表脚本
    */
    @Display(Name = "报表脚本")
    @Column("REPORT_SCRIPT")
    public String reportScript;

    /**
    * 图表配置
    */
    @Display(Name = "图表配置")
    @Column("CHARTS_CFG")
    public String chartsCfg;


    /**
    * 图表类型
    */
    @StringLength(50)
    @Display(Name = "图表类型")
    @Column("CHARTS_TYPE")
    public String chartsType;


    /**
    * 统计范围
    */
    @Display(Name = "统计范围")
    @Column("FILTR_LEVEL")
    public short filtrLevel;

    /**
    * 过滤配置
    */
    @Display(Name = "过滤配置")
    @Column("FILTR_STR")
    public String filtrStr;


    /**
    * 说明
    */
    @Display(Name = "说明")
    @Column("REMARK")
    public String remark;

    /**
    * 最新时间
    */
    @StringLength(50)
    @Display(Name = "最新时间")
    @Column("NEW_DATA")
    public String newData;
}
