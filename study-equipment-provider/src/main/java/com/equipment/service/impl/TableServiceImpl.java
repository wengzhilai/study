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

import java.util.Date;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    MyBatisService<FaTableTypeEntity> moduleMhs;

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

        if (inEnt.data.id == 0) {
            if (moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = moduleMhs.getIncreasingId(moduleEh);
                moduleEh.data.addTime=new Date();
            }
            resultObj.data = moduleMhs.insert(moduleEh, inEnt.saveFieldList, null);
        } else {
            FaTableTypeEntity old=moduleMhs.getSingleByPrimaryKey(moduleEh,moduleEh.data.id);
            resultObj.data = moduleMhs.update(moduleEh, inEnt.saveFieldList, inEnt.whereList);
            resultObj.success = resultObj.data > 0;

            if(!old.tableName.equals(moduleEh.data.tableName)){

            }
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
    public String MakeSqlCreateTable(FaTableTypeEntity inEnt)
    {
//        List<String> allColumns = new List<>();
//        foreach (var item in inEnt.AllColumns)
//        {
//            allColumns.Add(
//                    String.format("\r\n  {0} {1} {2} COMMENT '{3}'",
//                            item.columnName,
//                            GetTypeStr(item),
//                            (item.isRequired > 1) ? "not null" : "null",
//                            item.name
//                    )
//            );
//        }
//
//        String reObj = @"
//        create table {0}(
//            Id INT NOT NULL AUTO_INCREMENT,
//        {1},
//        PRIMARY KEY ( Id )
//);
//        ";
//        reObj = String.format(reObj, inEnt.TABLE_name, String.Join(",", allColumns));
        return "";
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
                "alter table {0}  modify column {1} {2} {3} COMMENT '{4}';",
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
                "alter table {0}  add {1} {2} {3} COMMENT '{4}';",
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
                "alter table {0}  change {5} {1} {2} {3} COMMENT '{4}';",
                tableName,
                inEnt.columnName,
                GetTypeStr(inEnt),
                (inEnt.isRequired > 1) ? "not null" : "null",
                inEnt.name,
                oldname
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
                "alter table {0}  add UNIQUE ({1})",
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
