package com.user.provider.server.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.dependencies.mybatis.service.MyBatisService;
import com.wzl.commons.model.KV;
import com.wzl.commons.model.dto.query.QueryRowBtnShowCondition;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.user.provider.server.QueryService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.TypeChange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    MyBatisService<FaQueryEntity> dapper;

    EntityHelper<FaQueryEntity> moduleEh = new EntityHelper<>(new FaQueryEntity());

    @Override
    public ResultObj<FaQueryEntity> singleByKey(int key) {
        moduleEh.data.id = key;
        ResultObj<FaQueryEntity> resultObj=new ResultObj<FaQueryEntity>(true);
        resultObj.data=dapper.getSingleByPrimaryKey(moduleEh, key);
        return resultObj;
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = dapper.delete(moduleEh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaQueryEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>(true);

        moduleEh.data = inEnt.data;
        if (inEnt.data.id == 0) {
            if (moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = dapper.getIncreasingId(moduleEh);
            }
            resultObj.data = dapper.insert(moduleEh, null, null);
        } else {
            if(inEnt.whereList==null || inEnt.whereList.size()==0){
                inEnt.whereList=Arrays.asList("id");
            }
            resultObj.data = dapper.update(moduleEh, inEnt.saveFieldList, inEnt.whereList);
            resultObj.success = resultObj.data > 0;
        }
        return resultObj;
    }

    public ResultObj<FaQueryEntity> GetSingleQuery(String code) {
        ResultObj<FaQueryEntity> resultObj = new ResultObj<>();

        resultObj.dataList = dapper.getAll(moduleEh, x -> x.code == code);
        if (resultObj.dataList != null && resultObj.dataList.size() > 0) {
            resultObj.data = resultObj.dataList.get(0);
            resultObj.success = true;
        } else {
            resultObj.success = false;
        }
        return resultObj;
    }

    public ResultObj<HashMap<String,Object>> getListData(QuerySearchDto inEnt) {
        ResultObj<HashMap<String,Object>> reObj = new ResultObj<>(true);
        String code=inEnt.code;
        FaQueryEntity query = dapper.getSingle(moduleEh,i -> i.code == code);
        if (query == null)
        {
            return reObj;
        }

        String whereStr = MakeWhereStr(inEnt);
        String AllSql = MakeSql(inEnt, query.queryConf);
        if (StringUtils.isAllBlank(inEnt.getOrderStr())) inEnt.setOrderStr( "(SELECT 0)");
        reObj.msg = MakePageSql(AllSql, inEnt.page, inEnt.rows, inEnt.orderStr, whereStr,null);
        try
        {
            String[] sqlList = reObj.msg.split(";");
            if (sqlList.length > 0)
            {
                reObj.dataList = dapper.Select(sqlList[0]);
            }

            if (sqlList.length > 1)
            {
                reObj.total = dapper.exec(sqlList[1]);
            }
        }
        catch(Exception e)
        {
            return reObj;
        }
        return reObj;
    }


    protected String MakeSql(QuerySearchDto inEnt, String querySql) {

        if (inEnt.paraList == null) inEnt.paraList = new ArrayList<>();
        if (inEnt.whereList == null) {
            if (StringUtils.isAnyBlank(inEnt.whereListStr)) {
                inEnt.whereList = new ArrayList<>();
            } else {
                inEnt.whereList = TypeChange.jsonStrToJavaBeanList(inEnt.whereListStr, QueryRowBtnShowCondition.class);
            }
        }

        if (inEnt.paraList == null) {
            if (StringUtils.isAnyBlank(inEnt.paraListStr)) {
                inEnt.paraList = new ArrayList<>();
            } else {
                inEnt.paraList = TypeChange.jsonStrToJavaBeanList(inEnt.paraListStr, KV.class);
            }
        }
        //替换地址参数
        for (KV tmp : inEnt.paraList) {
            if (tmp.v.equals("@(NOWDATA)"))
            {
                tmp.k = DateTime.now().toString("yyyy-MM-dd");
            }
            querySql = querySql.replace("@(" + tmp.k + ")", tmp.v);
        }
 

        return querySql;
    }

    protected String MakeWhereStr(QuerySearchDto inEnt) {
        if(inEnt==null || inEnt.whereList==null || inEnt.whereList.size()==0){
            return "";
        }
//        StringBuilder whereSb = new StringBuilder();
        List<String> whereList=new ArrayList<>();
        for (QueryRowBtnShowCondition tmp : inEnt.whereList.stream().filter(x -> x.opType != null && !StringUtils.isAnyBlank(x.opType) && !StringUtils.isAnyBlank(x.value)).collect(Collectors.toList()))
        {
            if (tmp.fieldType == null) tmp.fieldType = "string";
            if (tmp.opType.equals("is null") || tmp.opType.equals("is not null")) {
                whereList.add(String.format(" %s %s ", tmp.fieldName, tmp.opType));
                continue;
            }

            String nowType = tmp.fieldType.toLowerCase();
            int subIndex = tmp.fieldType.indexOf(".");
            if (subIndex > -1)
            {
                nowType = nowType.substring(subIndex + 1);
            }
            switch (nowType)
            {
                case "string":
                    switch (tmp.opType)
                    {
                        case "in":
                            whereList.add(String.format(" %s %s ('%s') ", tmp.fieldName, tmp.opType, tmp.value.replace(",", "','")));
                            break;
                        default:
                            if (tmp.opType.equals("like")) tmp.value = "%" + tmp.value + "%";
                            whereList.add(String.format(" %s %s '%s' ", tmp.fieldName, tmp.opType, tmp.value));
                            break;
                    }
                    break;
                case "date":
                case "datetime":
                    switch (tmp.opType)
                    {
                        case "not between":
                        case "between":
                            whereList.add(String.format(" %s %s '%s' ", tmp.fieldName, tmp.opType, StringUtils.join(Arrays.stream(tmp.value.split("~")).map(x -> x.trim()),"' and '")));
                            break;
                        default:
                            whereList.add(String.format(" %s %s '%s' ", tmp.fieldName, tmp.opType, tmp.value));
                            break;
                    }
                    break;
                default:
                    if (tmp.opType.equals("in"))
                    {
                        whereList.add(String.format(" %s %s ('%s') ", tmp.fieldName, tmp.opType, tmp.value.replace(",", "','")));
                    }
                    else
                    {
                        whereList.add(String.format(" %s %s %s ", tmp.fieldName, tmp.opType, tmp.value));
                    }
                    break;
            }
        }

        return String.join(" and ",whereList);
    }

    protected String MakePageSql(String sql, int pageIndex , int pageSize , String orderStr, String whereStr, List<String> fieldList)
    {
        if (pageIndex < 0) pageIndex = 1;
        if (pageSize < 0) pageSize = 10;
        if (!StringUtils.isAnyBlank(whereStr) && !whereStr.trim().toLowerCase().startsWith("where")) whereStr = "where " + whereStr;
        if (StringUtils.isAnyBlank(orderStr)) orderStr = "1";
        orderStr = String.format("ORDER BY %s", orderStr);


        String withStr = "";
        String WithStartStr = "-----WithStart-----";
        String WithEndStr = "-----WithEnd-----";
        int WithStartP = sql.indexOf(WithStartStr);
        int WithEndP = sql.indexOf(WithEndStr);
        if (WithStartP > -1 && WithEndP > -1 && WithEndP > WithStartP)
        {
            withStr = sql.substring(WithStartStr.length(), WithEndP - WithStartP - WithStartStr.length());
            sql = sql.substring(WithEndP + WithEndStr.length());
        }


        String t1File = "*";
        String tFile = "*";
        if (fieldList != null)
        {
            t1File = StringUtils.join(fieldList,",T1.");
            tFile = StringUtils.join(",T.", fieldList);
        }
        String sqlStr = "        %6$s\n" +
                "        SELECT T.%7$s FROM\n" +
                "            (\n" +
                "                    %1$s\n" +
                "            ) T %5$s %2$s LIMIT %3$s,%4$s;\n" +
                "        SELECT COUNT(1) ALL_NUM FROM (%1$s) T %5$s";
        if (pageIndex == 0) pageIndex = 1;
        if (pageSize == 0) pageSize = 10;
        int startNum = (pageIndex - 1) * pageSize;
        sqlStr = String.format(sqlStr,sql, orderStr, startNum, startNum + pageSize, whereStr, withStr, tFile, t1File);
        return sqlStr;
    }

    public byte[] downFile(QuerySearchDto inEnt) {
        String code=inEnt.code;
        FaQueryEntity query = dapper.getSingle(moduleEh,i -> i.code == code);
        if (query == null)
        {
            return null;
        }

        String whereStr = MakeWhereStr(inEnt);
        String AllSql = MakeSql(inEnt, query.queryConf);

        JSONObject jsonObj= TypeChange.jsonStrToJsonObject(query.queryCfgJson);

        if (StringUtils.isAllBlank(inEnt.getOrderStr())) inEnt.setOrderStr( "(SELECT 0)");
        String sql = MakePageSql(AllSql, 1, 10000, inEnt.orderStr, whereStr,null);
        try
        {
            String[] sqlList = sql.split(";");
            if (sqlList.length > 0)
            {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int i=0;
                List<Map> list=dapper.SelectMap(sqlList[0]);
                for (HashMap<String,Object> item : dapper.Select(sqlList[0])) {
                    if(i==0){
                        String str=String.join("," ,item.keySet().stream().map(x->Convert.toStr(jsonObj.getJSONObject(x).getString("title").replace(",","_"))).collect(Collectors.toList()))+"\r\n";
                        bos.write(str.getBytes());

                    }
                    String str=String.join("," ,item.values().stream().map(x->x==null?",": Convert.toStr(x).replace(",","_")).collect(Collectors.toList()))+"\r\n";
                    bos.write(str.getBytes());
                    i++;
                }
                return bos.toByteArray();
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return null;
    }

    //——代码分隔线——
}