package com.user.provider.model.entity;

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
    @Column("NAME")
    public String name;
    /**
     * 地址
     */
    @StringLength(2000)
    @Display(Name = "地址")
    @Column("LOCATION")
    public String location;
    /**
     * 代码
     */
    @StringLength(20)
    @Display(Name = "代码")
    @Column("CODE")
    public String code;
    /**
     * 调试
     */
    @Required
    @Display(Name = "调试")
    @Column("IS_DEBUG")
    public int is_debug;
    /**
     * 隐藏
     */
    @Required
    @Display(Name = "隐藏")
    @Column("IS_HIDE")
    public int is_hide;
    /**
     * 排序
     */
    @Required
    @Display(Name = "排序")
    @Column("SHOW_ORDER")
    public int show_order;
    /**
     * 描述
     */
    @StringLength(2000)
    @Display(Name = "描述")
    @Column("DESCRIPTION")
    public String description;
    /**
     * 图片
     */
    @StringLength(2000)
    @Display(Name = "图片")
    @Column("IMAGE_URL")
    public String image_url;
    /**
     * 桌面角色
     */
    @StringLength(200)
    @Display(Name = "桌面角色")
    @Column("DESKTOP_ROLE")
    public String desktop_role;
    /**
     * 宽
     */
    @Display(Name = "宽")
    @Column("W")
    public Integer w;
    /**
     * 高
     */
    @Display(Name = "高")
    @Column("H")
    public Integer h;

    /**
     * 所有子项
     */
    public List<FaModuleEntity> children;
}
