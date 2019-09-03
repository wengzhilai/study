package com.wjbjp.mapper;

import com.wjbjp.retention.EntityHelper;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MapperHelper {

    /**
     * 执行SQL
     *
     * @param sql
     * @return
     */
    @Select("${sql}")
    int exec(@Param("sql") String sql);


    @SelectProvider(type = MybatisSQLTemplate.class, method = "getSingleSql")
    HashMap<String,Object> GetSingleByPrimaryKey(@Param("data") EntityHelper eh,int key);

    @SelectProvider(type = MybatisSQLTemplate.class, method = "getSingleSql")
    HashMap<String, Object> GetSingleObj(@Param("data") EntityHelper eh,String whereStr);

    @SelectProvider(type = MybatisSQLTemplate.class, method = "getAll")
    List<HashMap<String,Object>> getAll(@Param("data") EntityHelper eh,String whereStr,int pageIndex,int pageSize, List<String> allItems);

    @InsertProvider(type = MybatisSQLTemplate.class, method = "insert")
    int insert(@Param("data") EntityHelper eh,List<String> saveFieldList, List<String> ignoreFieldList);

    @InsertProvider(type = MybatisSQLTemplate.class, method = "update")
    int update(@Param("data") EntityHelper eh,List<String> saveFieldList,String whereStr);

    @InsertProvider(type = MybatisSQLTemplate.class, method = "updateByList")
    int updateByList(@Param("data") EntityHelper eh,List<String> saveFieldList,List<String> whereList);


    @DeleteProvider(type = MybatisSQLTemplate.class, method = "delete")
    int delete(@Param("data") EntityHelper eh,String whereStr);

    @SelectProvider(type = MybatisSQLTemplate.class, method = "count")
    int count(@Param("data") EntityHelper eh,String whereStr);

    @SelectProvider(type = MybatisSQLTemplate.class, method = "oneValue")
    String oneValue(@Param("data") EntityHelper eh, String selectStr, String whereStr);
}
