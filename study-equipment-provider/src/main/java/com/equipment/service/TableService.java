package com.equipment.service;

import com.equipment.model.entity.FaTableColumnEntity;
import com.equipment.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.KVT;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;

public interface TableService {
    /**
    * 获取表的单个对象
    */
    FaTableTypeEntity SingleByKey(int key);

    /**
    * 保存和自定义Table
    * 包括列，对列只更新不删除，但可以禁用和户用
    * 保存成功后，生成创建脚本和修改脚本对物理表的列修改和添加
    */
   ResultObj<Integer> Save(DtoSave<FaTableTypeEntity> inEnt);

    /**
    * 删除
    */
    ResultObj<Integer> Delete(int key);

    /**
    *  获取所有自定义表的列表
    */
    ResultObj<KVT> GetTableSelect();


    /**
    * 生成创建表SQL
    */
    String MakeSqlCreateTable(FaTableTypeEntity inEnt);

    /**
    * 修改字段类型和注释
    * alter table {0}  modify column description varchar(255) null COMMENT '应用描述';
    */
    String MakeSqlAlterTable(String tableName,FaTableColumnEntity inEnt);

    /**
    * 生成添加字段字段
    * alert table sys_application add `url` varchar(255) not null comment '应用访问地址';
    */
    String MakeSqlAlterAddColumn(String tableName,FaTableColumnEntity inEnt);

    /**
    * 修改字段名字
    * alter table {0} change name app_name varchar(255) not null comment '应用访问地址';
    */
    String MakeSqlAlterChangeColumn(String tableName,String oldName,FaTableColumnEntity inEnt);

    /**
    * MySQL 修改字段为不可重复
    * ALTER TABLE dbname.table ADD UNIQUE (fieldname);
    */
    String MakeSqlAlterUniqueColumn(String tableName,FaTableColumnEntity inEnt);
}
