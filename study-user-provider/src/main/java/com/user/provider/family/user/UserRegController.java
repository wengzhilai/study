package com.user.provider.family.user;

import com.user.provider.family.BaseController;
import com.wzl.commons.model.entity.*;
import com.wzl.commons.model.RegDto;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.TokenUser;
import com.wzl.commons.utlity.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("UserReg")
public class UserRegController extends BaseController {



    @ApiOperation(value="注册用户", notes="根据url的id来指定删除用户")
    @RequestMapping(value = "/Reg" , method = RequestMethod.POST)
    public Result Reg(@RequestBody RegDto inobj) {
        Result reObj = new Result();
        reObj.msg=inobj.name;
        TokenUser tu=new TokenUser();
        tu.setName(inobj.name);
        tu.setPassword(inobj.pwd);
        String token=TokenUtil.createTokenForUser(tu);
        reObj.code=token;
        return reObj;
    }

    @RequestMapping(value = "/JwtTest" , method = RequestMethod.POST)
    public Result JwtTest() {
        Result reObj = new Result();
        reObj.msg = this.CurrUser.getName();
        return reObj;
    }

    @RequestMapping(value = "/JwtTestNo" , method = RequestMethod.POST)
    public Result JwtTestNo() {
        Result reObj = new Result();
        return reObj;
    }


    @RequestMapping(value = "/GetUserMap" , method = RequestMethod.POST)
    public ResultObj<Map> GetUserMap() {
        ResultObj<Map> reObj = new ResultObj();
        FaLoginEntity ent=new FaLoginEntity();
        ent.loginName="翁志来";
//        reObj.data= EntityHelper.getEntityMap(ent);
//        reObj.data.put("TableName",EntityHelper.getTableName(ent));
        return reObj;
    }
}
