package com.user.consumer.feign.impl;

import com.user.consumer.feign.TestService;
import com.wzl.commons.model.DtoDo;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String echo(String message) {
        return null;
    }

    @Override
    public DtoDo test(DtoDo inobj) {
        DtoDo reObj=new DtoDo();
        reObj.key="网络有问题1";
        return reObj;
    }

    @Override
    public String test1(DtoDo inobj) {
        return null;
    }

    @Override
    public String test2(String inobj) {
        return null;
    }
}
