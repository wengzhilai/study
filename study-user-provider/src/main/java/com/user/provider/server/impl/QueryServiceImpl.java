package com.user.provider.server.impl;

import cn.hutool.core.date.DateTime;
import com.dependencies.mybatis.service.MyBatisService;
import com.wzl.commons.model.KV;
import com.wzl.commons.model.dto.DataGridDataJson;
import com.wzl.commons.model.dto.query.QueryCfg;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    MyBatisService<FaQueryEntity> dapper;

    EntityHelper<FaQueryEntity> moduleEh = new EntityHelper<>(new FaQueryEntity());

    @Override
    public FaQueryEntity singleByKey(int key) {
        moduleEh.data.id = key;
        return dapper.getSingleByPrimaryKey(moduleEh, key);
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = dapper.delete(moduleEh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaQueryEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();
        moduleEh.data = inEnt.data;
        if (inEnt.data.id == 0) {
            if (moduleEh.data.id == 0 && moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = dapper.getIncreasingId(moduleEh);
            }
            resultObj.data = dapper.insert(moduleEh, inEnt.saveFieldList, null);
        } else {
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

    public ResultObj<DataGridDataJson> getListData(QuerySearchDto inEnt) {
        ResultObj<DataGridDataJson> reObj = new ResultObj<>();
        DataGridDataJson reEnt = new DataGridDataJson();
        FaQueryEntity query = dapper.getSingle(moduleEh,i -> i.code == inEnt.code);
        if (query == null)
        {
            return reObj;
        }
        List<QueryCfg> cfg = TypeChange.jsonStrToJavaBeanList(query.queryCfgJson,QueryCfg.class);

        String whereStr = MakeWhereStr(inEnt);
        String AllSql = MakeSql(inEnt, query.queryConf);
        if (StringUtils.isAllBlank(inEnt.getOrderStr())) inEnt.setOrderStr( "(SELECT 0)");
        reObj.msg = MakePageSql(AllSql, inEnt.page, inEnt.rows, inEnt.orderStr, whereStr,null);
        try
        {
            String[] sqlList = reObj.msg.split(";");
            if (sqlList.length > 0)
            {
//                reEnt.rows = DapperHelper.GetDataTable(sqlList[0]);
            }

            if (sqlList.length > 1)
            {
                int allNum = 0;
//                int.TryParse(DapperHelper.ExecuteScalarAsync(sqlList[1]), out allNum);
                reEnt.total = allNum;
            }
            reObj.data = reEnt;
        }
        catch(Exception e)
        {
//            LogHelper.WriteErrorLog(this.GetType(),"执行分页数据失败",e);
            return reObj;
        }
        return reObj;
    }


    public String MakeSql(QuerySearchDto inEnt, String querySql) {

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
            if (tmp.v == "@(NOWDATA)")
            {
                tmp.k = DateTime.now().toString("yyyy-MM-dd");
            }
            querySql = querySql.replace("@(" + tmp.k + ")", tmp.v);
        }
 

        return querySql;
    }

    public String MakeWhereStr(QuerySearchDto inEnt) {

        StringBuilder whereSb = new StringBuilder();
        for (QueryRowBtnShowCondition tmp : inEnt.whereList.stream().filter(x -> x.opType != null && !StringUtils.isAnyBlank(x.opType) && !StringUtils.isAnyBlank(x.value)).collect(Collectors.toList()))
        {
            if (tmp.fieldType == null) tmp.fieldType = "string";
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
                            whereSb.append(String.format(" %s %s ('%s') and ", tmp.objFiled, tmp.opType, tmp.value.replace(",", "','")));
                            break;
                        default:
                            if (tmp.opType == "like") tmp.value = "%" + tmp.value + "%";
                            whereSb.append(String.format(" %s %s '%s' and ", tmp.objFiled, tmp.opType, tmp.value));
                            break;
                    }
                    break;
                case "date":
                case "datetime":
                    switch (tmp.opType)
                    {
                        case "not between":
                        case "between":
                            whereSb.append(String.format(" %s %s '%s' and ", tmp.objFiled, tmp.opType, StringUtils.join(Arrays.stream(tmp.value.split("~")).map(x -> x.trim()),"' and '")));
                            break;
                        default:
                            whereSb.append(String.format(" %s %s '%s' and ", tmp.objFiled, tmp.opType, tmp.value));
                            break;
                    }
                    break;
                default:
                    if (tmp.opType == "in")
                    {
                        whereSb.append(String.format(" {0} {1} ('{2}') and ", tmp.objFiled, tmp.opType, tmp.value.replace(",", "','")));
                    }
                    else
                    {
                        whereSb.append(String.format(" {0} {1} {2} and ", tmp.objFiled, tmp.opType, tmp.value));
                    }
                    break;
            }
        }
        if (whereSb.length() > 4)
            whereSb = whereSb.delete(whereSb.length() - 4, 4);
        return whereSb.toString();
    }

    public String MakePageSql(String sql, int pageIndex , int pageSize , String orderStr, String whereStr, List<String> fieldList)
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
        String sqlStr = "        {5}\n" +
                "        SELECT T.{6} FROM\n" +
                "            (\n" +
                "                    {0}\n" +
                "            ) T {4} {1} LIMIT {2},{3};\n" +
                "        SELECT COUNT(1) ALL_NUM FROM ({0}) T {4}";
        if (pageIndex == 0) pageIndex = 1;
        if (pageSize == 0) pageSize = 10;
        int startNum = (pageIndex - 1) * pageSize;
        sqlStr = String.format(sqlStr, String.format(sql, whereStr), orderStr, startNum, startNum + pageSize, whereStr, withStr, tFile, t1File);
        return sqlStr;
    }
}
