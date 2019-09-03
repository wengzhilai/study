package com.wjbjp.controller;

import com.wjbjp.feign.service.TestUserProvider;
import com.wjbjp.model.DtoDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("Test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestUserProvider testC;

    @RequestMapping(value = "test", method = RequestMethod.POST)
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