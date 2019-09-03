package com.wjbjp.mapper;

import com.wjbjp.retention.EntityHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MybatisSQLTemplate {

    public String getSingleSql(@Param("data") EntityHelper eh,int key) {
        SQL sqlObj = new SQL().
                SELECT(StringUtils.join(eh.getSelectFiledList(null), ","))
                .FROM(eh.tableName)
                .LIMIT(1)
                .ORDER_BY(eh.dbKey + " DESC");
        String whereStr = eh.dbKey + "=" + key;
        if (!StringUtils.isAnyBlank(whereStr)) {
            sqlObj = sqlObj.WHERE(whereStr);
        }
        String sql = sqlObj.toString();
        System.out.println(sql);
        return sql;
    }

    public String getAll(@Param("data") EntityHelper eh,int pageIndex,int pageSize,String whereStr, List<String> allItems) {
        if (pageIndex == 0) pageIndex = 1;
        if (pageSize == 0) pageSize = 10;
        SQL sqlObj = new SQL().
                SELECT(StringUtils.join(eh.getSelectFiledList(allItems), ",")).
                FROM(eh.tableName).
                OFFSET((pageIndex - 1) * pageSize).
                LIMIT(pageSize).
                ORDER_BY(eh.dbKey+" DESC");

        if (!StringUtils.isAnyBlank(whereStr)) sqlObj = sqlObj.WHERE(whereStr);
        String sql = sqlObj.toString();
        System.out.println(sql);
        return sql;
    }

    public String insert(@Param("data") EntityHelper eh,List<String> saveFieldList, List<String> ignoreFieldList){
        HashMap<String, String> map= eh.getEntityMapMybits(saveFieldList,ignoreFieldList);
        String sql = new SQL()
                .INSERT_INTO(eh.tableName)
                .VALUES(StringUtils.join(map.keySet(),","),StringUtils.join(map.values(),","))
                .toString();
        System.out.println(sql);
        return sql;
    }

    public String update(@Param("data") EntityHelper eh,List<String> saveFieldList,String whereStr){
        HashMap<String, String> map= eh.getEntityMapMybits(saveFieldList,null);
        List<String> upItem=new ArrayList<>();
        for (String key : map.keySet()) {
            upItem.add(key+"="+map.get(key));
        }
        SQL sqlObj = new SQL()
                .UPDATE(eh.tableName)
                .SET(StringUtils.join(upItem,","));

        if (!StringUtils.isAnyBlank(whereStr)) sqlObj = sqlObj.WHERE(whereStr);
        String sql = sqlObj.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateByList(@Param("data") EntityHelper eh,List<String> saveFieldList,List<String> whereList){
        HashMap<String, String> mapSave= eh.getEntityMapMybits(saveFieldList,null);
        List<String> upItem=new ArrayList<>();
        for (String key : mapSave.keySet()) {
            upItem.add(key+"="+mapSave.get(key));
        }
        SQL sqlObj = new SQL()
                .UPDATE(eh.tableName)
                .SET(StringUtils.join(upItem,","));

        if (whereList!=null && whereList.size()>0){

            HashMap<String, String> mapWhere= eh.getEntityMapMybits(whereList,null);
            List<String> whereItem=new ArrayList<>();
            for (String key : mapWhere.keySet()) {
                whereItem.add(key+"="+mapWhere.get(key));
            }
            sqlObj = sqlObj.WHERE(StringUtils.join(whereItem,","));
        }
        String sql = sqlObj.toString();
        System.out.println(sql);
        return sql;
    }

    public String delete(@Param("data") EntityHelper eh,String whereStr) {
        SQL sqlObj = new SQL()
                .DELETE_FROM(eh.tableName);
        if (!StringUtils.isAnyBlank(whereStr)) sqlObj = sqlObj.WHERE(whereStr);
        String sql = sqlObj.toString();
        System.out.println(sql);
        return sql;
    }

    public String count(@Param("data") EntityHelper eh,String whereStr) {
        return oneValue(eh,"count(1) num",whereStr);
    }

    public String oneValue(@Param("data") EntityHelper eh,String selectStr,String whereStr) {
        SQL sqlObj = new SQL()
                .SELECT(selectStr)
                .FROM(eh.tableName);
        if(!StringUtils.isAnyBlank(whereStr)){
            sqlObj=sqlObj.WHERE(whereStr);
        }
        String sql=sqlObj.toString();
        System.out.println(sql);
        return sql;
    }
}
