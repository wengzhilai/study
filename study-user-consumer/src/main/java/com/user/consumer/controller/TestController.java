package com.user.consumer.controller;

import com.user.consumer.feign.TestService;
import com.wzl.commons.model.DtoDo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("Test")
public class TestController {
//    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestService testC;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试传入参数和传出参数")
    public DtoDo test(@RequestBody DtoDo inobj) {
        return testC.test(inobj);
    }

    @RequestMapping(value = "test1", method = RequestMethod.POST)
    public String test1(@RequestBody DtoDo inobj) {
        return testC.test1(inobj);
    }

    @RequestMapping(value = "test2", method = RequestMethod.POST)
    public String test2(@RequestBody String inobj) {
        return testC.test2(inobj);
    }

//    @RequestMapping(value = "rtTest", method = RequestMethod.POST)
//    public DtoDo rtTest(@RequestBody DtoDo inobj) {
//        logger.error("11111111111");
//        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
//        String url = String.format("http://%s:%s/Test/test", serviceInstance.getHost(), serviceInstance.getPort(), inobj);
//        return restTemplate.postForObject(url,inobj, DtoDo.class);
//    }
//
//    @RequestMapping(value = "rtTest1", method = RequestMethod.POST)
//    public String rtTest1(@RequestBody DtoDo inobj) {
//        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
//        String url = String.format("http://%s:%s/Test/test1", serviceInstance.getHost(), serviceInstance.getPort(), inobj);
//        return restTemplate.postForObject(url,inobj, String.class);
//    }
//
//    @RequestMapping(value = "rtTest2", method = RequestMethod.POST)
//    public String rtTest2(@RequestBody String inobj) {
//        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
//        String url = String.format("http://%s:%s/Test/test2", serviceInstance.getHost(), serviceInstance.getPort(), inobj);
//        return restTemplate.postForObject(url,inobj, String.class);
//    }
}