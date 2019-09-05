package com.equipment.test;

import com.equipment.controller.QueryController;
import com.equipment.controller.TableController;
import com.wzl.commons.utlity.TypeChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppLicationTests {
    @Autowired
    TableController tc;

    @Autowired
    QueryController qc;

    @Test
    public void GetSingle() {
        System.out.println(TypeChange.objToString(tc.SingleByKey(8)));
    }

    @Test
    public void GetTableSelect() {
        System.out.println(TypeChange.objToString(tc.GetTableSelect()));
    }

    @Test
    public void Query() {
        System.out.println(TypeChange.objToString(qc.Query("" +
                "SELECT ID id,NAME name,TABLE_NAME tableName,INTRODUCE introduce,ADD_TIME addTime,STAUTS stauts\n" +
                "FROM fa_table_type")));
    }


}
