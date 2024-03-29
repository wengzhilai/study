package com.dependencies.mybatis.service.impl;


import com.alibaba.fastjson.JSON;
import com.dependencies.mybatis.mapper.MapperHelper;
import com.dependencies.mybatis.model.entity.SequenceEntity;
import com.dependencies.mybatis.service.MyBatisService;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.lambda2sql.Lambda2Sql;
import com.wzl.commons.utlity.lambda2sql.SqlPredicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public int exec(String sql) {
        int reInt = mh.exec(sql);
        return reInt;
    }

    public boolean alter(String sql) {

        try {
            Object reInt = mh.alter(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public List<HashMap<String,Object>> Select(String sql){
        return mh.Select(sql);
    }
    public List<HashMap<String,Object>> execByMap(String sql,Map<String, Object> map){
        return mh.execByMap(sql,map);
    }

    public List<Map> SelectMap(String sql){
        return mh.SelectMap(sql);
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
    public List<T> getAll(EntityHelper<T> entityHelper, String whereStr) {
        return getAll(entityHelper, whereStr, 1, 100, null);
    }

    public List<T> getAll(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return getAll(entityHelper, whereStr);
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
        if(whereList==null || whereList.size()==0){
            return 0;
        }
        int reInt = mh.updateByList(entityHelper, saveFieldList,whereList);
        return reInt;
    }

    public int update(EntityHelper<T> entityHelper, List<String> saveFieldList, String whereStr) {
        if(StringUtils.isAnyBlank(whereStr)){
            return 0;
        }
        int reInt = mh.update(entityHelper, saveFieldList,whereStr);
        return reInt;
    }

    @Override
    public int update(EntityHelper<T> entityHelper, String whereStr) {
        if(StringUtils.isAnyBlank(whereStr)){
            return 0;
        }
        HashMap<String, String> map= entityHelper.getEntityMapNameColumn();
        List<String> saveFieldList=new ArrayList<>(map.keySet());

        //移除自增加ID
        if(entityHelper.dbKeyType!=DatabaseGeneratedOption.Computed){
            saveFieldList.remove(entityHelper.classKey);
        }

        int reInt = mh.update(entityHelper,saveFieldList, whereStr);
        return reInt;
    }

    @Override
    public int update(EntityHelper<T> entityHelper, SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return update(entityHelper, whereStr);
    }

    public int update(EntityHelper<T> entityHelper, List<String> saveFieldList,  SqlPredicate<T> whereLambda) {
        String whereStr = LambdaToSql(entityHelper, whereLambda);
        return update(entityHelper,saveFieldList, whereStr);
    }

    public int delete(EntityHelper<T> entityHelper, String whereStr) {
        if(StringUtils.isAnyBlank(whereStr)){
            return 0;
        }
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
    public int getIncreasingId(EntityHelper<T> entityHelper) {
        String tableName=entityHelper.tableName;
        EntityHelper<SequenceEntity> ehSeq= new EntityHelper<>(SequenceEntity.class);
        HashMap<String, Object> map= mh.GetSingleObj(ehSeq,"seq_name='"+tableName+"'");
        String str = JSON.toJSONString(map);
        SequenceEntity ent = JSON.parseObject(str, SequenceEntity.class);
        if(ent==null){
            String maxId=oneValue(entityHelper,"MAX("+entityHelper.dbKey+")",null);
            ent=new SequenceEntity();
            ent.current_val=Integer.parseInt(maxId)+1;
            ent.seq_name=tableName;
            ent.increment_val=1;
            mh.insert(new EntityHelper<>(ent),null,null);
            return ent.current_val;
        }
        else {
            ent.current_val+=1;
            List<String> saveList=Arrays.asList("current_val");
            SqlPredicate<SequenceEntity> whereLambda=x->x.seq_name==tableName;
            String whereStr = LambdaToSql(ehSeq, whereLambda);
            mh.update(new EntityHelper<>(ent),saveList,whereStr);
        }
        return ent.current_val;
    }



    @Override
    public String oneValue(EntityHelper eh, String selectStr, String whereStr) {
        return mh.oneValue(eh, selectStr,whereStr);
    }
}
