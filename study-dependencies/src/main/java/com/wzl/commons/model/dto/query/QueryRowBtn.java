package com.wzl.commons.model.dto.query;

import com.wzl.commons.model.KV;
import com.wzl.commons.retention.*;
import lombok.Data;

import java.util.List;

@Data
public class QueryRowBtn {
    /**
    * 按钮名
    */
    @Display(Name = "按钮名")
    public String name;

    /**
    * 样式
    */
    @Display(Name = "样式")
    public String iconCls;

    /**
    * 地址
    */
    @Display(Name = "地址")
    public String url;

    /**
    * 对话框模式
    */
    @Display(Name = "对话框模式")
    public String dialogMode;

    /**
    * 对话框宽
    */
    @Display(Name = "对话框宽")
    public String dialogWidth;

    /**
    * 对话框宽
    */
    @Display(Name = "对话框高")
    public String dialogHeigth;

    /**
    * 显示条件
    */
    @Display(Name = "显示条件")
    public List<QueryRowBtnShowCondition> showCondition;

    /**
    * 传值参数
    */
    @Display(Name = "参数")
    public List<KV> parameter;
}
