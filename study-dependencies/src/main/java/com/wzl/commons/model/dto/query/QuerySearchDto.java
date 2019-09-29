package com.wzl.commons.model.dto.query;

import com.wzl.commons.model.KV;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class QuerySearchDto {
    /**
    * 排序字段
    */
    public String sort ;
    /**
    * 排序类型 asc|desc
    */
    public String order ;
    /**
     * 代码
     */
    public String code ;
    /**
    * 当前页码
    */
    public int page ;
    /**
    * 每页显示数
    */
    public int rows ;


    public List<KV> paraList ;
    public List<QueryRowBtnShowCondition> whereList ;
    public String whereListStr ;
    public String paraListStr ;

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }
    public String orderStr ;
    public String getOrderStr(){
        if (StringUtils.isAnyBlank(sort))
        {
            order="asc";
        }
        if (StringUtils.isAnyBlank(sort))
        {
            return "";
        }
        return  String.format("%s %s", sort, order);
    }
}
