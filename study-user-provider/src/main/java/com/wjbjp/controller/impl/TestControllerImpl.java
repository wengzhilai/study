package com.wjbjp.controller.impl;

import com.alibaba.fastjson.JSON;
import com.wjbjp.controller.TestController;
import com.wjbjp.family.BaseController;
import com.wzl.commons.model.DtoDo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("Test")
public class TestControllerImpl  extends BaseController implements TestController {
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试传入参数和传出参数")
    public DtoDo test(@RequestBody DtoDo inobj) {
        DtoDo reObj = new DtoDo();
        reObj.key = inobj.key;
        return reObj;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试传入参数和传出参数")
    public String test1(DtoDo inobj) {
        String reStr = JSON.toJSONString(inobj);
        return reStr;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试传入参数和传出参数")
    public String test2(String inobj) {
        return "__" + inobj + "_";
    }
}
