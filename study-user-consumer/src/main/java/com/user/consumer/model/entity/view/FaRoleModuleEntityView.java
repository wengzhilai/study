package com.user.consumer.model.entity.view;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import lombok.Data;

/**
* 用户扩展
*/
@Data
    @Table("fa_role_module a left join \n"+
            "        fa_role b on a.ROLE_ID=b.ID left join\n" +
            "        fa_module c on a.MODULE_ID=c.ID")
public class FaRoleModuleEntityView {
    /**
     * 角色ID
     */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Display(Name = "角色ID")
    @Column("a.ROLE_ID")
    public int roleId;


    /**
    * 角色名
    */
    @Display(Name = "角色名")
    @Column("b.NAME")
    public String roleName;



    /**
    * 模块ID
    */
    @Display(Name = "模块ID")
    @Column("a.MODULE_ID")
    public int moduleId;

    /**
    * 上级
    */
    @Display(Name = "上级")
    @Column("c.PARENT_ID")
    public Integer parentId;
    /**
    * 模块名
    */
    @StringLength(60)
    @Display(Name = "模块名")
    @Column("c.NAME")
    public String name;
    /**
    * 地址
    */
    @StringLength(2000)
    @Display(Name = "地址")
    @Column("c.LOCATION")
    public String location;
    /**
    * 代码
    */
    @StringLength(20)
    @Display(Name = "代码")
    @Column("c.CODE")
    public String code;
    /**
    * 调试
    */
    @Required
    @Display(Name = "调试")
    @Column("c.IS_DEBUG")
    public Integer isDebug;
    /**
    * 隐藏
    */
    @Required
    @Display(Name = "隐藏")
    @Column("c.IS_HIDE")
    public int isHide;
    /**
    * 排序
    */
    @Required
    @Display(Name = "排序")
    @Column("c.SHOW_ORDER")
    public int showOrder;
    /**
    * 描述
    */
    @StringLength(2000)
    @Display(Name = "描述")
    @Column("c.DESCRIPTION")
    public String description;
    /**
    * 图片
    */
    @StringLength(2000)
    @Display(Name = "图片")
    @Column("c.IMAGE_URL")
    public String imageUrl;
    /**
    * 桌面角色
    */
    @StringLength(200)
    @Display(Name = "桌面角色")
    @Column("c.DESKTOP_ROLE")
    public String desktopRole;
    /**
    * 宽
    */
    @Display(Name = "宽")
    @Column("c.W")
    public Integer w;
    /**
    * 高
    */
    @Display(Name = "高")
    @Column("c.H")
    public Integer h;
}
