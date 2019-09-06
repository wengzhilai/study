package com.equipment.consumer.test;

import com.equipment.consumer.controller.TestController;
import com.wzl.commons.retention.EntityHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentConsumerApplicationTests {

    @Autowired
    TestController tc;

    @Test
    public void test2() {
        System.out.println(tc.test2("ddd"));
    }
}
