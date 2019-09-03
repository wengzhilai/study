package com.equipment.service.impl;


import com.alibaba.fastjson.JSON;
import com.equipment.mapper.MapperHelper;
import com.equipment.service.MyBatisService;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.lambda2sql.Lambda2Sql;
import com.wzl.commons.utlity.lambda2sql.SqlPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MyBatisServiceImpl<T> implements MyBatisService<T> {

    @Autowired
    private MapperHelper mh;

    private <TT> String LambdaToSql(EntityHelper<TT> entityHelper, SqlPredicate<TT> whereLambda) {
        String whereStr = Lambda2Sql.toSql(whereLambda);
        Pattern p = Pattern.compile("\\{(.*?)\\}");//正则表达式，取{和}之间的字符串，不包括=和|
        Matcher m = p.matcher(whereStr);
        HashMap<String, String> allItem = entityHelper.getEntityMapNameColumn();
        while (m.find()) {
            whereStr = whereStr.replaceAll("(\\{*)" + m.group(1) + "(\\}*)", allItem.get(m.group(1)));
        }
        return whereStr;
    }

    public T getSingleByPrimaryKey(EntityHelper<T> entityHelper,int key) {
        HashMap<String, Object> map = mh.GetSingleByPrimaryKey(entityHelper,key);
        String str = JSON.toJSONString(map);
        T ent = JSON.parseObject(str, entityHelper.classType);
        return ent;
    }

    public T getSingle(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);

        List<HashMap<String, Object>> map = mh.getAll(entityHelper, whereStr, 1, 1, null);
        String str = JSON.toJSONString(map);
        List<T> list = JSON.parseArray(str, entityHelper.classType);
        if (list == null || list.size() == 0) return null;
        return list.get(0);
    }

    public List<T> getAll(EntityHelper<T> entityHelper, String whereStr, int pageIndex, int pageSize, List<String> allItems) {
        List<HashMap<String, Object>> map = mh.getAll(entityHelper, whereStr, pageIndex, pageSize, allItems);
        String str = JSON.toJSONString(map);
        List<T> ent = JSON.parseArray(str, entityHelper.classType);
        return ent;
    }

    public List<T> getAll(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda, int pageIndex, int pageSize, List<String> allItems) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return getAll(entityHelper, whereStr, pageIndex, pageSize, allItems);
    }

    public int insert(EntityHelper<T> entityHelper, List<String> saveFieldList, List<String> ignoreFieldList) {
        int reInt = mh.insert(entityHelper, saveFieldList, ignoreFieldList);
        return reInt;
    }

    public int update(EntityHelper<T> entityHelper, List<String> saveFieldList,List<String> whereList) {
        int reInt = mh.updateByList(entityHelper, saveFieldList,whereList);
        return reInt;
    }

    public int update(EntityHelper<T> entityHelper, List<String> saveFieldList, String whereStr) {
        int reInt = mh.update(entityHelper, saveFieldList,whereStr);
        return reInt;
    }

    public int update(EntityHelper<T> entityHelper, List<String> saveFieldList,  SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return update(entityHelper,saveFieldList, whereStr);
    }

    public int delete(EntityHelper<T> entityHelper, String whereStr) {
        int reInt = mh.delete(entityHelper, whereStr);
        return reInt;
    }

    public int delete(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return delete(entityHelper, whereStr);
    }

    public int count(EntityHelper<T> entityHelper, String whereStr) {
        int reInt = mh.count(entityHelper, whereStr);
        return reInt;
    }

    public int count(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return count(entityHelper, whereStr);
    }


    @Override
    public String oneValue(EntityHelper eh, String selectStr, String whereStr) {
        return mh.oneValue(eh, selectStr,whereStr);
    }
}
