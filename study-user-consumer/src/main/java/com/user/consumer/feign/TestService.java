package com.user.consumer.feign;

import com.user.consumer.feign.impl.TestServiceImpl;
import com.wzl.commons.model.DtoDo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-provider",url = "http://localhost:9001", fallback = TestServiceImpl.class)
public interface TestService {
    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable("message") String message);

    @GetMapping(value = "/Test/test")
    DtoDo test(@RequestBody DtoDo inobj);

    @GetMapping(value = "/Test/test1")
    String test1(@RequestBody DtoDo inobj);

    @GetMapping(value = "/Test/test2")
    String test2(@RequestBody String inobj);
}

