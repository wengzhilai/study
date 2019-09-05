package com.equipment.controller.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.equipment.controller.QueryController;
import com.equipment.model.entity.FaEquipmentEntity;
import com.wzl.commons.model.ResultObj;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class QueryControllerImpl implements QueryController {
    @Autowired
    MyBatisService<FaEquipmentEntity> batis;

    @RequestMapping(value = "/Query", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据")
    public ResultObj<HashMap<String,Object>> Query(String sql) {
        ResultObj<HashMap<String,Object>> resultObj=new ResultObj<>();

        List<HashMap<String,Object>> reList= batis.Select(sql);
        resultObj.dataList=reList;
        return resultObj;
    }
}
