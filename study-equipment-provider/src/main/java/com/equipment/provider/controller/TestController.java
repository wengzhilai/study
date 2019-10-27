package com.equipment.provider.controller;

import com.wzl.commons.model.DtoDo;
import org.springframework.web.bind.annotation.RequestBody;

public interface TestController {
    DtoDo test(@RequestBody DtoDo inobj);

    String test1(@RequestBody DtoDo inobj);

    String test2(@RequestBody String inobj);
}
