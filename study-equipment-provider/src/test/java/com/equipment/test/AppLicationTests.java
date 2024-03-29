package com.equipment.test;

import com.equipment.provider.controller.QueryController;
import com.equipment.provider.controller.TableController;

import com.wzl.commons.model.DtoDo;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaTableColumnEntity;
import com.wzl.commons.model.entity.FaTableTypeEntity;
import com.wzl.commons.model.mynum.TableColumnType;
import com.wzl.commons.utlity.TypeChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppLicationTests {
    @Autowired
    TableController tc;

    @Autowired
    QueryController qc;

    @Test
    public void Save() {

        FaTableTypeEntity saveTable= new FaTableTypeEntity();
        DtoSave<FaTableTypeEntity> saveObj=new DtoSave<>(saveTable);
        saveObj.data.stauts="正常";
        saveObj.data.tableName="test_java2";
        saveObj.data.addTime=new Date();
        saveObj.data.introduce="介绍2";
        saveObj.data.name="测试java2";
        saveObj.data.id=30;
        saveObj.saveFieldList=Arrays.asList("stauts","tableName","addTime","introduce","name");
        saveObj.data.allColumns=new ArrayList<>();

        FaTableColumnEntity colum=new FaTableColumnEntity();
        colum.columnType= TableColumnType.Text;
        colum.name="Name";
        colum.columnName="Name";
        colum.introduce="测试java";
        colum.stauts="正常";
        saveObj.data.allColumns.add(colum);


        System.out.println(TypeChange.objToString(tc.Save(saveObj)));
    }

    @Test
    public void GetSingle() {
        System.out.println(TypeChange.objToString(tc.SingleByKey(new DtoDo("30"))));
    }

    @Test
    public void Delete() {
        System.out.println(TypeChange.objToString(tc.Delete(new DtoDo("30"))));
    }

    @Test
    public void GetTableSelect() {
        System.out.println(TypeChange.objToString(tc.GetTableSelect()));
    }

    @Test
    public void Query() {
        String tt=" 1=%0$s ,2=%0$s";
        tt=String.format(tt,11,22);
        System.out.println(tt);
    }


}
