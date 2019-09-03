package com.equipment.service.impl;

import com.equipment.model.entity.FaTableColumnEntity;
import com.equipment.model.entity.FaTableTypeEntity;
import com.equipment.service.MyBatisService;
import com.equipment.service.TableService;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    MyBatisService<FaTableTypeEntity> moduleMhs;

    @Autowired
    MyBatisService<FaTableColumnEntity> batisColumn;

    EntityHelper<FaTableTypeEntity> moduleEh = new EntityHelper<>(new FaTableTypeEntity());

    @Override
    public FaTableTypeEntity SingleByKey(int key) {
        return moduleMhs.getSingleByPrimaryKey(moduleEh, key);
    }

    @Transactional
    public ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();

        if (inEnt.data.AllColumns == null || inEnt.data.AllColumns.size() == 0)
        {
            resultObj.success = false;
            resultObj.msg = "配置列不能为空";
            return resultObj;
        }

        moduleEh = new EntityHelper(inEnt.data);
        boolean isAdd=true;
        if (inEnt.data.id == 0) {
            if (moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = moduleMhs.getIncreasingId(moduleEh);
                moduleEh.data.addTime=new Date();
            }
            isAdd=true;
            resultObj.data = moduleMhs.insert(moduleEh, inEnt.saveFieldList, null);
        } else {
            isAdd = false;

            FaTableTypeEntity oldEnt = moduleMhs.getSingleByPrimaryKey(moduleEh, moduleEh.data.id);
            resultObj.data = moduleMhs.update(moduleEh, inEnt.saveFieldList, inEnt.whereList);
            resultObj.success = resultObj.data > 0;

            if (!oldEnt.tableName.equals(moduleEh.data.tableName)) {
                moduleMhs.exec(this.MakeSqlChangetableName(oldEnt.tableName, inEnt.data.tableName));
            }
        }

        for (FaTableColumnEntity item : inEnt.data.AllColumns) {
//            if (isAdd || item.id == 0) //如果是新增加，或列ID为空
//            {
//                item.tableTypeId = inEnt.data.id;
//                item.id = batisColumn.getIncreasingId(new EntityHelper(new FaTableColumnEntity()));
//                int opNum = batisColumn.insert(new DtoSave<FaTableColumnEntity>
//                {
//                    Data = item
//                });
//                if (opNum < 1)
//                {
//                    LogHelper.WriteErrorLog(this.GetType(), "保存字段失败");
//                    DapperHelper.TranscationRollback();
//                    reObj.IsSuccess = false;
//                    reObj.Msg = "保存字段失败";
//                    return reObj;
//                }
//                //如果是修改，才修改数据库
//                if (!isAdd)
//                {
//                    //添加字段,线程添加
//                    var t = DapperHelper.Exec(MakeSqlAlterAddColumn(inEnt.Data.TABLE_NAME, item));
//                }
//            }
//            else
//            {
//                var oldCol = oldEnt.AllColumns.Single(x => x.ID == item.ID);
//                if (
//                        oldCol != null
//                                && oldCol.COLUMN_NAME == item.COLUMN_NAME
//                                && oldCol.NAME == item.NAME
//                                && oldCol.COLUMN_TYPE == item.COLUMN_TYPE
//                                && oldCol.COLUMN_LONG == item.COLUMN_LONG
//                )
//                {
//                    continue;
//                }
//                int opNum = 0;
//                if (oldCol == null)
//                {
//                    item.ID = await new SequenceRepository().GetNextID<FaTableColumnEntity>();
//                    opNum = await dapperCol.Save(new DtoSave<FaTableColumnEntity>
//                    {
//                        Data = item
//                    });
//                    //添加字段
//                    var t = DapperHelper.Exec(MakeSqlAlterAddColumn(inEnt.Data.TABLE_NAME, item));
//                }
//                else
//                {
//                    opNum = await dapperCol.Update(new DtoSave<FaTableColumnEntity>
//                    {
//                        Data = item,
//                                SaveFieldList = dapperCol.modelHelper.GetDirct().Select(x => x.Key).ToList(),
//                            IgnoreFieldList = null
//                    });
//                    if (oldCol.COLUMN_NAME == item.COLUMN_NAME)
//                    {
//                        var t = DapperHelper.Exec(MakeSqlAlterTable(inEnt.Data.TABLE_NAME, item));
//                    }
//                    else
//                    {
//                        var t = DapperHelper.Exec(MakeSqlAlterChangeColumn(inEnt.Data.TABLE_NAME, oldCol.COLUMN_NAME, item));
//                    }
//                }
//
//                if (opNum < 1)
//                {
//                    LogHelper.WriteErrorLog(this.GetType(), "修改字段失败");
//                    dbHelper.TranscationRollback();
//                    reObj.IsSuccess = false;
//                    reObj.Msg = "修改字段失败";
//                    return reObj;
//                }
//            }
        }




        return resultObj;


    }

    @Override
    public ResultObj<Integer> Delete(int key) {
        ResultObj<Integer> reObj = new ResultObj();
        reObj.data = moduleMhs.delete(moduleEh, x -> x.id == key);
        reObj.success = reObj.data > 0;
        return reObj;
    }

    @Override
    public ResultObj<KVT> GetTableSelect() {
        return null;
    }



    /**
    * 生成创建表SQL
    */
    public String MakeSqlCreateTable(FaTableTypeEntity inEnt) {
        List<String> allColumns = new ArrayList<>();
        for (FaTableColumnEntity item : inEnt.AllColumns) {
            allColumns.add(
                    String.format("\r\n  %s %s %s COMMENT '%s'",
                            item.columnName,
                            "",
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
        return reObj;
    }



    /**
    * 修改表名
    */
    public String MakeSqlChangetableName(String oldtableName, String nowtableName)
    {

        String reObj = String.format(
                "ALTER TABLE %s REname TO %s;",
                oldtableName,
                nowtableName
        );
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
            case "text":
            case "textarea":
            case "Checkbox":
            case "Radio":
            case "auto":
                return String.format("varchar(%s) CHARACTER SET utf8", inEnt.columnLong);
            case "int":
            case "pic":
                return String.format("int");
            case "datatime":
                return String.format("datatime");
            default:
                return String.format("varchar(%s) CHARACTER SET utf8", inEnt.columnLong);
        }
    }


}
