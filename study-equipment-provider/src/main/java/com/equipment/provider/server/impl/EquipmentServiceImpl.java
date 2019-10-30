package com.equipment.provider.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.equipment.provider.server.EquipmentService;
import com.equipment.provider.server.TableService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.dto.EquipmentDto;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.dto.smartTable.ColumnEditorSetting;
import com.wzl.commons.model.dto.smartTable.SmartTableColumnSetting;
import com.wzl.commons.model.dto.smartTable.SmartTableSetting;
import com.wzl.commons.model.entity.FaEquipmentEntity;
import com.wzl.commons.model.entity.FaTableColumnEntity;
import com.wzl.commons.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.QueryHelper;
import com.wzl.commons.utlity.TypeChange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.convert.Convert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    MyBatisService<FaEquipmentEntity> dapper;

    @Autowired
    MyBatisService<FaTableTypeEntity> dapperTable;

    @Autowired
    TableService tableService;

    EntityHelper<FaEquipmentEntity> eh = new EntityHelper<>(new FaEquipmentEntity());

    @Override
    public ResultObj<FaEquipmentEntity> singleByKey(DtoDo inEnt) {
        ResultObj<FaEquipmentEntity> reObj=new ResultObj<>(true);
        reObj.data=dapper.getSingleByPrimaryKey(eh, Convert.toInt(inEnt.key));
        return reObj;
    }

    @Override
    public Result delete(DtoDo inEnt) {
        Result reObj = new Result();
        Integer key = Convert.toInt(inEnt.key);
        reObj.success = dapper.delete(eh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaEquipmentEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();
        eh.data = inEnt.data;
        if(inEnt.whereList==null || inEnt.whereList.size()==0){
            inEnt.whereList=new ArrayList<>();
            inEnt.whereList.add("id");
        }

        if (inEnt.data.id == 0) {
            if (eh.dbKeyType == DatabaseGeneratedOption.Computed) {
                eh.data.id = dapper.getIncreasingId(eh);
                inEnt.saveFieldList.add("id");
            }
            resultObj.data = dapper.insert(eh, inEnt.saveFieldList, null);
        } else {
            if(inEnt.whereList==null || inEnt.whereList.size()==0){
                inEnt.whereList = Arrays.asList("id");
            }
            resultObj.data = dapper.update(eh, inEnt.saveFieldList, inEnt.whereList);
        }
        resultObj.success = resultObj.data > 0;

        return resultObj;
    }

    public ResultObj<KV> getTree(DtoDo inEnt) {
        ResultObj<KV> reObj=new ResultObj<> ();
        int id=Convert.toInt(inEnt.key);
        List<FaEquipmentEntity> list=dapper.getAll(eh,x->x.parentId==id);
        reObj.dataList=list.stream().map(x->new KV(Convert.toStr(x.id),x.name)).collect(Collectors.toList());
        for (KV item : reObj.dataList) {
            item.child = getTree(new DtoDo(item.k)).dataList;

        }

        reObj.success=true;
        return reObj;
    }

    public ResultObj<SmartTableSetting> getConfig(DtoDo inEnt) {
        ResultObj<SmartTableSetting> reObj=new ResultObj<> ();
        int id=Convert.toInt(inEnt.key);

        FaEquipmentEntity equType = dapper.getSingle(eh,x->x.id==id);
        if (equType == null)
        {
            reObj.success = false;
            reObj.msg = "设备类型ID有误";
            return reObj;
        }
        FaTableTypeEntity tableType = tableService.singleByKey(equType.tableTypeId).data;
        if (tableType == null)
        {
            reObj.success = false;
            reObj.msg = "表不存在";
            return reObj;
        }
        SmartTableSetting setting = new SmartTableSetting();
        setting.heardBtn = "[]";
        List<SmartTableColumnSetting> allColumns=new ArrayList<>();
        for (FaTableColumnEntity x : tableType.allColumns) {
            SmartTableColumnSetting t = new SmartTableColumnSetting();
            t.columnName = x.columnName;
            t.title = x.name;
            t.editable = true;
            t.filter = true;
            t.show = true;
            t.sort = true;
            t.type = x.columnType.toString().toLowerCase();
            t.editor=new ColumnEditorSetting();
            switch (x.columnType){
                case Int:
                    t.editor.type= "number";
                    break;
            }
            allColumns.add(t);
        }


        setting.columnsList = allColumns;
        setting.rowsBtn = "[]";
        setting.showCheckbox = true;
        reObj.data = setting;
        reObj.success=true;
        return reObj;
    }



    public ResultObj<HashMap<String,Object>> getConfigAndData(QuerySearchDto inEnt) {
        ResultObj<HashMap<String,Object>> reObj=new ResultObj<> (true);
        if (StringUtils.isAnyBlank(inEnt.code))
        {
            reObj.success = false;
            reObj.msg = "代码有误";
            return reObj;
        }
        FaEquipmentEntity equType = dapper.getSingleByPrimaryKey(eh,Convert.toInt(inEnt.code));
        if (equType == null)
        {
            reObj.success = false;
            reObj.msg = "设备类型ID有误";
            return reObj;
        }
        FaTableTypeEntity tableType = tableService.singleByKey(equType.tableTypeId).data;
        if (tableType == null)
        {
            reObj.success = false;
            reObj.msg = "表不存在";
            return reObj;
        }
        HashMap<String,Object> bindEnt = new HashMap<>();


        String sql = String.format("select Id id, %1$s  from %2$s",
                String.join(",", tableType.allColumns.stream().map(i -> i.columnName).collect(Collectors.toList())),
                tableType.tableName);

        String whereStr = QueryHelper.MakeWhereStr(inEnt);
        sql = QueryHelper.MakeSql(inEnt, sql);
        sql = QueryHelper.MakePageSql(sql, inEnt.page, inEnt.rows, inEnt.orderStr, whereStr,null);
        try
        {
            String[] sqlList = sql.split(";");
            if (sqlList.length > 0)
            {
                reObj.dataList = dapper.Select(sqlList[0]);
            }

            if (sqlList.length > 1)
            {
                reObj.total = dapper.exec(sqlList[1]);
            }

            reObj.data = bindEnt;
        }
        catch (Exception e)
        {
            reObj.success=false;
            reObj.msg=e.getMessage();
            return reObj;
        }
        return reObj;
    }

    public ResultObj<Integer> saveEquiment(EquipmentDto inEnt) {
        ResultObj<Integer> reObj=new ResultObj<> ();
        FaEquipmentEntity equType = dapper.getSingleByPrimaryKey(eh,Convert.toInt(inEnt.typeId));
        if (equType == null)
        {
            reObj.success = false;
            reObj.msg = "设备类型ID有误";
            return reObj;
        }
        FaTableTypeEntity tableType = tableService.singleByKey(equType.tableTypeId).data;
        if (tableType == null)
        {
            reObj.success = false;
            reObj.msg = "表不存在";
            return reObj;
        }
        String sql;
        Map<String, Object> parMap=TypeChange.jsonStrToJavaBean(inEnt.dataStr, Map.class);

        if(inEnt.id==0){
            sql = String.format("insert into %1$s(%2$s) values(%3$s) ",
                    tableType.tableName,
                    String.join(",", tableType.allColumns.stream().map(i -> i.columnName).collect(Collectors.toList())),
                    String.join(",", tableType.allColumns.stream().map(i -> "'${map." + i.columnName+"}'").collect(Collectors.toList()))
            );
            System.out.println(sql);
            System.out.println(TypeChange.objToString(parMap));

            dapper.execByMap(sql,parMap);
        }
        else {
            sql = String.format("update %1$s set %2$s where ID=%3$s",
                    tableType.tableName,
                    String.join(",", tableType.allColumns.stream().map(i -> i.columnName + "='${map." + i.columnName+"}'").collect(Collectors.toList())),
                    inEnt.id
            );
            System.out.println(sql);
            dapper.execByMap(sql,parMap);
        }
        reObj.success=true;
        return reObj;
    }

    /**
     * 删除设备
     * @param inEnt
     * @return
     */
    public ResultObj<Integer> deleteEquiment(EquipmentDto inEnt) {
        ResultObj<Integer> reObj = new ResultObj<>();
        FaEquipmentEntity equType = dapper.getSingleByPrimaryKey(eh, Convert.toInt(inEnt.typeId));
        if (equType == null) {
            reObj.success = false;
            reObj.msg = "设备类型ID有误";
            return reObj;
        }
        FaTableTypeEntity tableType = tableService.singleByKey(equType.tableTypeId).data;
        if (tableType == null) {
            reObj.success = false;
            reObj.msg = "表不存在";
            return reObj;
        }
        String sql = String.format("delete from %1$s where Id=%2$s",
                tableType.tableName,
                inEnt.id
        );
        System.out.println(sql);
        boolean opNum= dapper.alter(sql);
        reObj.success = true;
        return reObj;
    }

    //——代码分隔线——
}