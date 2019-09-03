package com.wjbjp.model.dto;

import com.wjbjp.model.KV;
import com.wjbjp.model.QueryCondition;
import lombok.Data;

import java.util.List;

@Data
public class EasyuiDataGridSearchDto {
    /**
    * 排序字段
    **/
    public String sort ;
    /**
    * 排序类型 asc|desc
    **/
    public String order ;

    /**
     * 查询代码
     */
    public String code ;
    /**
    * 当前页码
    **/
    public int page ;
    /**
    * 每页显示数
    **/
    public int rows ;

    /**
     * 参数
     */
    public List<KV> paraList ;
    public List<QueryCondition> WhereList ;
    public String WhereListStr ;
    public String ParaListStr ;
}
