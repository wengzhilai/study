package com.equipment.consumer.model.entity;

import com.equipment.consumer.model.mynum.TableColumnType;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

/**
 * 自定义表的列
*/
@Table("fa_table_column")
public class FaTableColumnEntity {

    /**
     * ID
     */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Display(Name = "ID")
    @Column("ID")
    public int id;

    /**
     * 自定义表ID
     */
    @Display(Name = "自定义表ID")
    @Column("TABLE_TYPE_ID")
    public int tableTypeId;

    /**
     * 列别名
     */
    @StringLength(50)
    @Display(Name = "列别名")
    @Column("NAME")
    public String name;

    /**
     * 数据库中列名
     */
    @StringLength(50)
    @Display(Name = "数据库中列名")
    @Column("COLUMN_NAME")
    public String columnName;

    /**
     * 介绍
     */
    @StringLength(50)
    @Display(Name = "介绍")
    @Column("INTRODUCE")
    public String introduce;

    /**
     * 状态,禁用，启用
     */
    @StringLength(15)
    @Display(Name = "状态")
    @Column("STAUTS")
    public String stauts;

    /**
     * 排序号
     */
    @Display(Name = "排序号")
    @Column("ORDER_INDEX")
    public int orderIndex;

    /**
     * 列类型，text,int,datatime,pic,textarea,Checkbox,Radio,auto
     */
    @StringLength(15)
    @Display(Name = "列类型")
    @Column("COLUMN_TYPE")
    public TableColumnType columnType;

    /**
     * 字段长度
     */
    @Display(Name = "字段长度")
    @Column("COLUMN_LONG")
    public int columnLong;

    /**
     * 必填
     */
    @Display(Name = "必填")
    @Column("IS_REQUIRED")
    public int isRequired;

    /**
     * 默认值
     */
    @StringLength(10)
    @Display(Name = "默认值")
    @Column("DEFAULT_VALUE")
    public String defaultValue;

    /**
     * 列配置内容
     */
    @StringLength(15)
    @Display(Name = "列配置内容")
    @Column("COLUMN_TYPE_CFG")
    public String columnTypeCfg;
    /**
     * 权限
     * * 获取权限列表
     * * 判断的权限，1添加，2修改，4查看
     */
    @Display(Name = "权限")
    @Column("AUTHORITY")
    public int authority;
}

