package com.wjbjp.feign.service;

import com.wjbjp.controller.TestController;
import com.wjbjp.model.DtoDo;
import org.springframework.stereotype.Component;

@Component
public class ServiceFallback implements TestUserProvider {
    @Override
    public String echo(String message) {
        return "网络有问题1";
    }

    @Override
    public DtoDo test(DtoDo inobj) {
        DtoDo reObj=new DtoDo();
        reObj.key="网络有问题1";
        return reObj;
    }

    @Override
    public String test1(DtoDo inobj) {
        return "网络有问题2";
    }

    @Override
    public String test2(String inobj) {
        return "网络有问题3";
    }
}
