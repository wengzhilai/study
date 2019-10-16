package com.user.consumer.controller.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.user.consumer.controller.QueryController;
import com.user.consumer.feign.ModuleService;
import com.user.consumer.feign.QueryService;
import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.*;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.utlity.TypeChange;
import feign.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("query")
public class QueryControllerImpl implements QueryController {

    @Autowired
    private QueryService service;

    @RequestMapping(value = "GetSingleQuery", method = RequestMethod.POST)
    @ApiOperation(value = "根据代码查询配置")
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj);
    }

    @RequestMapping(value = "getListData", method = RequestMethod.POST)
    @ApiOperation(value = "获取数据")
    public ResultObj<DataGridDataJson> getListData(QuerySearchDto inObj) {
        ResultObj<DataGridDataJson> resultObj=service.getListData(inObj);
        return resultObj;
    }

    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    @ApiOperation(value = "查询单个Query")
    public ResultObj<FaQueryEntity> singleByKey(@RequestBody DtoDo inEnt) {
        return service.singleByKey(Convert.toInt(inEnt.key));
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "保存Query")
    public ResultObj<Integer> save(@RequestBody DtoSave<FaQueryEntity> inEnt) {
        return service.save(inEnt);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除Query")
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(inEnt);
    }


    @RequestMapping(value = "downFile")
    @ApiOperation(value = "下载文件")
    public ResponseEntity<byte[]> downFile(String postJson){
        ResponseEntity<byte[]> result=null ;
        InputStream inputStream = null;
        try {
            // feign文件下载
            Response response = service.downFile(postJson);
            Response.Body body = response.body();
            inputStream = body.asInputStream();
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            Map<String, Collection<String>> allHeard=response.headers();
            HttpHeaders heads = new HttpHeaders();
//            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=123.txt");
//            heads.add(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
            for (String s : allHeard.keySet()) {
                heads.add(s,allHeard.get(s).toArray()[0].toString());
            }
            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    //——代码分隔线——
}