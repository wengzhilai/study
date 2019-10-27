package com.equipment.provider.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.equipment.provider.server.TableService;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaTableColumnEntity;
import com.wzl.commons.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    MyBatisService<FaTableTypeEntity> moduleMhs;

    @Autowired
    MyBatisService<FaTableColumnEntity> batisColumn;

    EntityHelper<FaTableTypeEntity> moduleEh = new EntityHelper<>(new FaTableTypeEntity());

    @Override
    public ResultObj<FaTableTypeEntity> SingleByKey(int key) {
        ResultObj<FaTableTypeEntity> reObj=new ResultObj<>();
        reObj.data=moduleMhs.getSingleByPrimaryKey(moduleEh, key);
        reObj.data.allColumns=batisColumn.getAll(new EntityHelper(new FaTableColumnEntity()),x->x.tableTypeId==key);
        reObj.success=true;
        return reObj;
    }

    @Transactional
    public ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();

        //region 判断数据是否有效
        if (inEnt.data.allColumns == null || inEnt.data.allColumns.size() == 0) {
            resultObj.success = false;
            resultObj.msg = "配置列不能为空";
            return resultObj;
        }
        //endregion

        moduleEh = new EntityHelper(inEnt.data);
        boolean isAdd;
        int opNum;
        boolean opBool;
        FaTableTypeEntity oldEnt = null;
        List<FaTableColumnEntity> oldColumnList=new ArrayList<>();

        //region 添加或修改数据
        if (inEnt.data.id == 0) {
            if (moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = moduleMhs.getIncreasingId(moduleEh);
                moduleEh.data.addTime = new Date();
            }
            isAdd = true;

            try {
                moduleEh.updateNullValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            resultObj.data = moduleMhs.insert(moduleEh, inEnt.saveFieldList, null);
            if (resultObj.data>0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                LogHelper.writeErrorLog(this.getClass(), "添加表名失败");
                resultObj.success = false;
                resultObj.msg = "添加表名失败";
                return resultObj;
            }
        } else {
            isAdd = false;
            oldEnt = moduleMhs.getSingleByPrimaryKey(moduleEh, moduleEh.data.id);
            List<HashMap<String,Object>> list=moduleMhs.Select(this.CheckTableExistsNum(oldEnt.tableName));
            if(list.size()==0){
                moduleMhs.alter(MakeSqlCreateTable(inEnt.data));
            }

            int id=moduleEh.data.id;
            oldColumnList=batisColumn.getAll(new EntityHelper(new FaTableColumnEntity()),x->x.tableTypeId==id,1,100,null);
            resultObj.data = moduleMhs.update(moduleEh, inEnt.saveFieldList, Arrays.asList("id"));
            resultObj.success = resultObj.data > 0;
            if (!oldEnt.tableName.equals(moduleEh.data.tableName)) {
                opBool=moduleMhs.alter(this.MakeSqlChangetableName(oldEnt.tableName, inEnt.data.tableName));
                if (!opBool) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    LogHelper.writeErrorLog(this.getClass(), "修改表名失败");
                    resultObj.success = false;
                    resultObj.msg = "修改字段失败";
                    return resultObj;
                }
            }
        }
        //endregion

        //region 修改阿或添加列
        for (FaTableColumnEntity item : inEnt.data.allColumns) {
            EntityHelper<FaTableColumnEntity> columEH = new EntityHelper(item);
            if (isAdd) //如果是新增加，或列ID为空
            {
                item.tableTypeId = inEnt.data.id;
                item.id = batisColumn.getIncreasingId(columEH);
                try {
                    columEH.updateNullValue();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                opNum = batisColumn.insert(columEH, null, null);
                if (opNum < 1) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    LogHelper.writeErrorLog(this.getClass(), "保存字段失败");
                    resultObj.success = false;
                    resultObj.msg = "保存字段失败";
                    return resultObj;
                }
            }
            //修改
            else {
                List<FaTableColumnEntity> oldColList=oldColumnList.stream().filter(x -> x.columnName.equals(item.columnName)).collect(Collectors.toList());
                FaTableColumnEntity oldCol = oldColList.size()>0 ? oldColList.get(0): null;
                if (
                        oldCol != null &&
                        oldCol.columnName == item.columnName &&
                        oldCol.name == item.name &&
                        oldCol.columnType == item.columnType &&
                        oldCol.columnLong == item.columnLong
                        ) {
                    continue;
                }
                if (oldCol == null) {
                    item.id = batisColumn.getIncreasingId(columEH);
                    try {
                        columEH.updateNullValue();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    opNum = batisColumn.insert(columEH, null, null);
                    if (opNum < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        LogHelper.writeErrorLog(this.getClass(), "添加字段失败");
                        resultObj.success = false;
                        resultObj.msg = "修改字段失败";
                        return resultObj;
                    }
                    //添加字段
                    opBool = batisColumn.alter(MakeSqlAlterAddColumn(inEnt.data.tableName, item));
                    if (!opBool) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        LogHelper.writeErrorLog(this.getClass(), "添加字段失败1");
                        resultObj.success = false;
                        resultObj.msg = "修改字段失败";
                        return resultObj;
                    }
                } else {
                    int oldId = oldCol.id;
                    opNum = batisColumn.update(columEH, x -> x.id == oldId);
                    if (opNum < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        LogHelper.writeErrorLog(this.getClass(), "修改字段失败");
                        resultObj.success = false;
                        resultObj.msg = "修改字段失败";
                        return resultObj;
                    }

                    if (oldCol.columnName.equals(item.columnName)) {
                        opBool = batisColumn.alter(MakeSqlAlterTable(inEnt.data.tableName, item));
                    } else {
                        opBool = batisColumn.alter(MakeSqlAlterChangeColumn(inEnt.data.tableName, oldCol.columnName, item));
                    }
                    if (!opBool) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        LogHelper.writeErrorLog(this.getClass(), "修改字段失败1");
                        resultObj.success = false;
                        resultObj.msg = "修改字段失败";
                        return resultObj;
                    }
                }


            }
        }
        //endregion

        for (FaTableColumnEntity item : oldColumnList){
            if(!inEnt.data.allColumns.stream().anyMatch(x->x.columnName.equals(item.columnName))){
                EntityHelper<FaTableColumnEntity> columEH = new EntityHelper(item);
                int delId=item.id;
                batisColumn.delete(columEH,x->x.id==delId );
                batisColumn.alter(MakeSqlAlterDropColumn(inEnt.data.tableName,item));
            }
        }

        //region 新增加的时候，创建新表
        if(isAdd){
            boolean obj = moduleMhs.alter(MakeSqlCreateTable(inEnt.data));
            if (!obj) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                LogHelper.writeErrorLog(this.getClass(), "创建新表失败");
                resultObj.success = false;
                resultObj.msg = "修改字段失败";
                return resultObj;
            }
        }
        //endregion

        return resultObj;
    }

    @Transactional
    public ResultObj<Integer> Delete(int key) {
        ResultObj<Integer> reObj = new ResultObj();
        batisColumn.delete(new EntityHelper(new FaTableColumnEntity()), x -> x.tableTypeId == key);

        reObj.data = moduleMhs.delete(moduleEh, x -> x.id == key);
        reObj.success = reObj.data > 0;
        return reObj;
    }

    @Override
    public ResultObj<KVT> GetTableSelect() {

        ResultObj<KVT> reObj = new ResultObj<>();
        List<FaTableTypeEntity> entList = moduleMhs.getAll(moduleEh,x->x.stauts=="正常",1,100,null);
        reObj.dataList =new ArrayList<>();
        entList.stream().forEach(i -> reObj.dataList.add(new KVT(String.valueOf(i.id),i.name)));
        return reObj;
    }



    /**
    * 生成创建表SQL
    */
    public String MakeSqlCreateTable(FaTableTypeEntity inEnt) {
        List<String> allColumns = new ArrayList<>();
        for (FaTableColumnEntity item : inEnt.allColumns) {
            allColumns.add(
                    String.format("\r\n  %s %s %s COMMENT '%s'",
                            item.columnName,
                            GetTypeStr(item),
                            (item.isRequired > 1) ? "not null" : "null",
                            item.name
                    )
            );
        }

        String reObj = "" +
                "create table %s(\n" +
                "   Id INT NOT NULL AUTO_INCREMENT,\n" +
                "%s,\n" +
                "   PRIMARY KEY ( Id )\n" +
                ");";
        reObj = String.format(reObj, inEnt.tableName, String.join(",", allColumns));
        System.out.println(reObj);
        return reObj;
    }

    /**
     * 判断表存在数
     * @param tableName
     * @return
     */
    public String CheckTableExistsNum(String tableName){
        String reObj="show tables like '%1$s'";
        reObj=String.format(reObj,tableName);
        return reObj;
    }

    /**
    * 修改表名
    */
    public String MakeSqlChangetableName(String oldtableName, String nowtableName)
    {

        String reObj = String.format(
                "ALTER TABLE %s rename TO %s;",
                oldtableName,
                nowtableName
        );
        System.out.println(reObj);
        return reObj;
    }

    /**
    * 修改字段类型和注释
    * alter table {0}  modify column description varchar(255) null COMMENT '应用描述';
    */
    public String MakeSqlAlterTable(String tableName, FaTableColumnEntity inEnt)
    {

        String reObj = String.format(
                "alter table %s  modify column %s %s %s COMMENT '%s';",
                tableName,
                inEnt.columnName,
                GetTypeStr(inEnt),
                (inEnt.isRequired > 1) ? "not null" : "null",
                inEnt.name
        );
        System.out.println(reObj);
        return reObj;
    }

    /**
    * 生成添加字段字段
    * alert table sys_application add `url` varchar(255) not null comment '应用访问地址';  
    */
    public String MakeSqlAlterAddColumn(String tableName, FaTableColumnEntity inEnt)
    {
        String reObj = String.format(
                "alter table %s  add %s %s %s COMMENT '%s';",
                tableName,
                inEnt.columnName,
                GetTypeStr(inEnt),
                (inEnt.isRequired > 1) ? "not null" : "null",
                inEnt.name
        );
        System.out.println(reObj);
        return reObj;
    }

    /**
    * 修改字段名字
    * alter table {0} change name app_name varchar(255) not null comment '应用访问地址'; 
    */
    public String MakeSqlAlterChangeColumn(String tableName, String oldname, FaTableColumnEntity inEnt)
    {
        String reObj = String.format(
                "alter table %s  change %s %s %s %s COMMENT '%s';",
                tableName,
                GetTypeStr(inEnt),
                (inEnt.isRequired > 1) ? "not null" : "null",
                inEnt.name,
                oldname,
                inEnt.columnName
                );
        System.out.println(reObj);
        return reObj;
    }

    /**
    * MySQL 修改字段为不可重复
    * ALTER TABLE dbname.table ADD UNIQUE (fieldname);
    */
    public String MakeSqlAlterUniqueColumn(String tableName, FaTableColumnEntity inEnt)
    {
        String reObj = String.format(
                "alter table %s  add UNIQUE (%s)",
                tableName,
                inEnt.columnName,
                GetTypeStr(inEnt),
                (inEnt.isRequired > 1) ? "not null" : "null",
                inEnt.name
        );
        System.out.println(reObj);
        return reObj;
    }

    /**
     * 删除列
     * @param tableName
     * @param inEnt
     * @return
     */
    public String MakeSqlAlterDropColumn(String tableName, FaTableColumnEntity inEnt)
    {
        String reObj = String.format(
                "alter table %s  drop column %s",
                tableName,
                inEnt.columnName
        );
        System.out.println(reObj);
        return reObj;
    }


    /**
     * 获取创建表的类型
     * @param inEnt
     * @return
     */
    public String GetTypeStr(FaTableColumnEntity inEnt)
    {
        //text,int,datatime,pic,textarea,Checkbox,Radio,auto
        if (inEnt.columnLong == 0) inEnt.columnLong = 50;
        switch (inEnt.columnType)
        {
            case Text:
            case Textarea:
            case Checkbox:
            case Radio:
            case Auto:
                return String.format("varchar(%s) CHARACTER SET utf8", inEnt.columnLong);
            case Int:
            case Pic:
                return String.format("int");
            case Datatime:
                return String.format("datatime");
            default:
                return String.format("varchar(%s) CHARACTER SET utf8", inEnt.columnLong);
        }
    }




}
