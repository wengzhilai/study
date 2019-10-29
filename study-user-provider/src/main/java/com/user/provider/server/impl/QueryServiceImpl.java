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

import com.wzl.commons.utlity.QueryHelper;

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

        String whereStr = QueryHelper.MakeWhereStr(inEnt);
        String AllSql = QueryHelper.MakeSql(inEnt, query.queryConf);
        if (StringUtils.isAllBlank(inEnt.getOrderStr())) inEnt.setOrderStr( "(SELECT 0)");
        reObj.msg = QueryHelper.MakePageSql(AllSql, inEnt.page, inEnt.rows, inEnt.orderStr, whereStr,null);
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



    public byte[] downFile(QuerySearchDto inEnt) {
        String code=inEnt.code;
        FaQueryEntity query = dapper.getSingle(moduleEh,i -> i.code == code);
        if (query == null)
        {
            return null;
        }

        String whereStr = QueryHelper.MakeWhereStr(inEnt);
        String AllSql = QueryHelper.MakeSql(inEnt, query.queryConf);

        JSONObject jsonObj= TypeChange.jsonStrToJsonObject(query.queryCfgJson);

        if (StringUtils.isAllBlank(inEnt.getOrderStr())) inEnt.setOrderStr( "(SELECT 0)");
        String sql = QueryHelper.MakePageSql(AllSql, 1, 10000, inEnt.orderStr, whereStr,null);
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