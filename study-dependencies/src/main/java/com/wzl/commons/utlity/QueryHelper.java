package com.wzl.commons.utlity;

import cn.hutool.core.date.DateTime;
import com.wzl.commons.model.KV;
import com.wzl.commons.model.dto.query.QueryRowBtnShowCondition;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Query查询
 */
public class QueryHelper {
    public static String MakeSql(QuerySearchDto inEnt, String querySql) {

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

    public static String MakeWhereStr(QuerySearchDto inEnt) {
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

    public static String MakePageSql(String sql, int pageIndex , int pageSize , String orderStr, String whereStr, List<String> fieldList)
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
}
