package com.user.provider.controller.impl;

import cn.hutool.core.convert.Convert;
import com.user.provider.controller.QueryController;
import com.wzl.commons.model.dto.*;
import com.wzl.commons.model.dto.query.QuerySearchDto;
import com.wzl.commons.model.entity.*;
import com.user.provider.server.ModuleService;
import com.user.provider.server.QueryService;
import com.wzl.commons.model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("query")
public class QueryControllerImpl implements QueryController {

    @Autowired
    QueryService service;

    @ApiOperation(value="获取Query所有内容")
    @RequestMapping(value = "GetSingleQuery", method = RequestMethod.POST)
    public ResultObj<FaQueryEntity> GetSingleQuery(@RequestBody DtoDo inObj) {
        return service.GetSingleQuery(inObj.key);
    }

    @ApiOperation(value="获取Query所有数据")
    @RequestMapping(value = "getListData", method = RequestMethod.POST)
    public ResultObj<DataGridDataJson> getListData(@RequestBody QuerySearchDto inObj) {
        ResultObj<DataGridDataJson> resultObj=service.getListData(inObj);
        return resultObj;
    }

    @ApiOperation(value="查询单个Query")
    @RequestMapping(value = "singleByKey", method = RequestMethod.POST)
    public ResultObj<FaQueryEntity> singleByKey(@RequestBody int inEnt) {
        return service.singleByKey(inEnt);
    }

    @ApiOperation(value="保存Query")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultObj<Integer> save(@RequestBody DtoSave<FaQueryEntity> inEnt) {
        return service.save(inEnt);
    }

    @ApiOperation(value="删除Query")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(@RequestBody DtoDo inEnt) {
        return service.delete(Convert.toInt(inEnt.key));
    }


    @ApiOperation(value="下载文件")
    @RequestMapping(value = "downFile", method = RequestMethod.POST)
    public String downFile(@RequestBody QuerySearchDto inEnt,HttpServletResponse response) {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + inEnt.code + ".csv");// 设置文件名
        byte[] buffer = service.downFile(inEnt);
        FileInputStream fis = null;
        try {
            OutputStream os = response.getOutputStream();
            os.write(buffer);
            return "下载成功";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载失败";
    }

    //——代码分隔线——
}