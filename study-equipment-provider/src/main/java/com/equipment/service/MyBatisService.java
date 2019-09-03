package com.equipment.service;


import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.lambda2sql.SqlPredicate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyBatisService<T> {
    T getSingleByPrimaryKey(EntityHelper<T> entityHelper, int key);

    T getSingle(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda);

    List<T> getAll(EntityHelper<T> entityHelper, String whereStr, int pageIndex, int pageSize, List<String> allItems);

    List<T> getAll(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda, int pageIndex, int pageSize, List<String> allItems);

    int insert(EntityHelper<T> entityHelper, List<String> saveFieldList, List<String> ignoreFieldList);

    int delete(EntityHelper<T> entityHelper, String whereStr);

    int delete(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda);

    int count(EntityHelper<T> entityHelper, String whereStr);

    int count(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda);

    int update(EntityHelper<T> entityHelper, List<String> saveFieldList, List<String> whereList);

    int update(EntityHelper<T> entityHelper, List<String> saveFieldList, String whereStr);

    int update(EntityHelper<T> entityHelper, List<String> saveFieldList, SqlPredicate<T> whereLambda);



    /**
     * 获取一个对象，如，min,max,count
     *
     * @param eh
     * @param selectStr
     * @param whereStr
     * @return
     */
    String oneValue(@Param("data") EntityHelper eh, String selectStr, String whereStr);

}
