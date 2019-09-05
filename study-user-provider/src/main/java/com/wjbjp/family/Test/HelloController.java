package com.wjbjp.family.Test;

import com.wzl.commons.model.DtoDo;
import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器
 *
 */
@RestController
@RequestMapping("Hello")
public class HelloController {

    @RequestMapping(value = "/test" , method = RequestMethod.POST)
    public DtoDo test(@RequestBody DtoDo inobj) {
        DtoDo reObj = new DtoDo();
        reObj.key = inobj.key;
        return reObj;
    }
}