package com.equipment.consumer.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.List;

@Table("fa_module")
public class FaModuleEntity {
    /**
     * ID
     */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.None)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;
    /**
     * 上级
     */
    @Display(Name = "上级")
    @Column("PARENT_ID")
    public Integer parent_id;
    /**
     * 模块名
     */
    @StringLength(60)
    @Display(Name = "模块名")
    @Column("ID")
    public String name;
    /**
     * 地址
     */
    @StringLength(2000)
    @Display(Name = "地址")
    @Column("ID")
    public String location;
    /**
     * 代码
     */
    @StringLength(20)
    @Display(Name = "代码")
    @Column("ID")
    public String code;
    /**
     * 调试
     */
    @Required
    @Display(Name = "调试")
    @Column("ID")
    public int is_debug;
    /**
     * 隐藏
     */
    @Required
    @Display(Name = "隐藏")
    @Column("ID")
    public int is_hide;
    /**
     * 排序
     */
    @Required
    @Display(Name = "排序")
    @Column("ID")
    public int show_order;
    /**
     * 描述
     */
    @StringLength(2000)
    @Display(Name = "描述")
    @Column("ID")
    public String description;
    /**
     * 图片
     */
    @StringLength(2000)
    @Display(Name = "图片")
    @Column("ID")
    public String image_url;
    /**
     * 桌面角色
     */
    @StringLength(200)
    @Display(Name = "桌面角色")
    @Column("ID")
    public String desktop_role;
    /**
     * 宽
     */
    @Display(Name = "宽")
    @Column("ID")
    public Integer w;
    /**
     * 高
     */
    @Display(Name = "高")
    @Column("ID")
    public Integer h;

    /**
     * 所有子项
     */
    public List<FaModuleEntity> children;
}
