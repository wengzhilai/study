package com.wjbjp.family;

import com.alibaba.fastjson.JSON;
import com.wjbjp.controller.LoginController;
import com.wjbjp.controller.TestController;
import com.wjbjp.mapper.MapperHelper;
import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.EditPwdDto;
import com.wjbjp.model.dto.LogingDto;
import com.wjbjp.model.dto.ResetPasswordDto;
import com.wjbjp.model.entity.FaLoginEntity;
import com.wjbjp.model.entity.FaUserEntity;
import com.wjbjp.retention.EntityHelper;
import com.wjbjp.server.MapperHelperService;
import com.wjbjp.utlity.lambda2sql.Lambda2Sql;
import com.wjbjp.utlity.lambda2sql.SqlPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyApplicationTests {
   @Autowired
    TestController tc;

    @Autowired
    LoginController login;

    @Autowired
    MapperHelper _mh;

    @Autowired
    MapperHelperService<FaLoginEntity> mhs;

    @Test
    public void TestSingleByPrimaryKey() {
        FaLoginEntity fle=new FaLoginEntity();
        fle.id=1;
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        FaLoginEntity ent=mhs.getSingleByPrimaryKey(eh);
        System.out.println(ent);
    }

    @Test
    public void GetSingle() {
        FaLoginEntity fle=new FaLoginEntity();
        fle.id=1;
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        FaLoginEntity ent=mhs.getSingle(eh,a-> a.id==33);
        System.out.println(ent);
    }

    @Test
    public void TestAll(){
        FaLoginEntity fle=new FaLoginEntity();
        fle.id=1;
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        List<FaLoginEntity> ent=mhs.getAll(eh,x ->x.id==33 ,1,10, null);
        System.out.println(ent);
    }


    @Test
    public void insert(){
        FaLoginEntity fle=new FaLoginEntity();
        fle.id=2;
        fle.loginName="234";
        fle.password="123";
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        int ent=mhs.insert(eh,null,null);
        System.out.println(ent);
    }

    @Test
    public void delete(){
        FaLoginEntity fle=new FaLoginEntity();
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        int ent=mhs.delete(eh,"id=2");
        System.out.println(ent);
    }

    @Test
    public void lambda(){
        int a=12;
        String pwd="abcdef".toLowerCase();
        SqlPredicate<FaLoginEntity> l= x->x.id==a && (x.password==pwd || x.loginName=="234");

        String filetext= Lambda2Sql.toSql(l);
        System.out.println(filetext);

        Pattern p = Pattern.compile("\\{(.*?)\\}");//正则表达式，取{和}之间的字符串，不包括=和|
        Matcher m = p.matcher(filetext);
        HashMap<String, String> rePlaceMap = new HashMap<>();
        while(m.find()) {
            rePlaceMap.put(m.group(1),m.group(0));
        }
        System.out.println(rePlaceMap);
    }

    @Test
    public void count() {
        FaLoginEntity fle = new FaLoginEntity();
        EntityHelper<FaLoginEntity> eh=new EntityHelper(fle);
        int ent = mhs.count(eh,(FaLoginEntity x) -> {
            return x.id == 33 && x.loginName == "aa";
        });
        System.out.println(ent);
    }



    @Test
    public void deleteUser() {
        Result reObj = login.deleteUser("13408565627");
        System.out.println(JSON.toJSONString(reObj));
    }

    @Test
    public void getIncreasingId() {
        EntityHelper<FaLoginEntity> fle = new EntityHelper<FaLoginEntity>(FaLoginEntity.class);
        int id= mhs.getIncreasingId(fle);
        System.out.println(id);
    }
    @Test
    public void loginReg() {
        LogingDto dd = new LogingDto();
        dd.userName = "翁志来";
        dd.loginName="18180770311";
        dd.password = "123456";
        ResultObj<Integer> reObj = login.loginReg(dd);
        System.out.println(JSON.toJSONString(reObj));
    }

    @Test
    public void login() {
        LogingDto dd = new LogingDto();
        dd.userName = "18180770311";
        dd.password = "123456";
        ResultObj<FaUserEntity> reObj = login.userLogin(dd);
        System.out.println(JSON.toJSONString(reObj));
    }

    @Test
    public void resetPassword() {
        ResetPasswordDto dd = new ResetPasswordDto();
        dd.loginName="18180770311";
        dd.newPwd = "123456";
        dd.verifyCode = "1234";
        Result reObj = login.resetPassword(dd);
        System.out.println(JSON.toJSONString(reObj));
    }

    @Test
    public void userEditPwd() {
        EditPwdDto dd = new EditPwdDto();
        dd.loginName="18180770311";
        dd.newPwd = "1234561";
        dd.oldPwd="1234561";
        Result reObj = login.userEditPwd(dd);
        System.out.println(JSON.toJSONString(reObj));
    }

    @Test
    public void userEditLoginName() {
        Result reObj = login.userEditLoginName("18180770308","18180770309");
        System.out.println(JSON.toJSONString(reObj));
    }

}
