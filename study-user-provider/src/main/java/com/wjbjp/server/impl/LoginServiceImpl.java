package com.wjbjp.server.impl;

import java.lang.*;
import java.util.Arrays;
import java.util.List;

import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.TokenUser;
import com.wjbjp.model.dto.DtoSave;
import com.wjbjp.model.dto.EditPwdDto;
import com.wjbjp.model.dto.LogingDto;
import com.wjbjp.model.dto.ResetPasswordDto;
import com.wjbjp.model.entity.FaLoginEntity;
import com.wjbjp.model.entity.FaUserEntity;
import com.wjbjp.model.mynum.DatabaseGeneratedOption;
import com.wjbjp.retention.EntityHelper;
import com.wjbjp.server.LoginService;
import com.wjbjp.server.MapperHelperService;
import com.wjbjp.utlity.StringHelper;
import com.wjbjp.utlity.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    MapperHelperService<FaUserEntity> userMhs;

    @Autowired
    MapperHelperService<FaLoginEntity> loginMhs;

    @Transactional
    public ResultObj<Integer> loginReg(LogingDto inEnt) {
        ResultObj<Integer> reObj = new ResultObj(true);

        //region 验证输入是否有误
        boolean isExists = checkUser(inEnt.loginName).data;
        if (isExists) {
            reObj.success = false;
            reObj.msg = "用户已经存在";
            return reObj;
        }

        isExists = checkLogin(inEnt.loginName).data;
        if (isExists) {
            reObj.success = false;
            reObj.msg = "账号已经存在";
            return reObj;
        }
        //endregion

        //region 添加账号
        EntityHelper<FaLoginEntity> login = new EntityHelper<>(new FaLoginEntity());
        login.data.loginName = inEnt.loginName;
        login.data.failCount = 0;
        login.data.password = StringHelper.getMD5(inEnt.password);
        login.data.isLocked = 0;
        if (login.data.id == 0 && login.dbKeyType == DatabaseGeneratedOption.Computed) {
            login.data.id = loginMhs.getIncreasingId(login);
        }
        loginMhs.insert(login, null, null);
        //endregion

        //region 添加用户
        EntityHelper<FaUserEntity> userHp = new EntityHelper<>(new FaUserEntity());
        userHp.data.loginName = inEnt.loginName;
        userHp.data.name = inEnt.userName;
        userHp.data.createTime = new java.util.Date();
        userHp.data.isLocked = 0;
        userHp.data.districtId = 1;
        if (userHp.data.id == 0 && userHp.dbKeyType == DatabaseGeneratedOption.Computed) {
            userHp.data.id = userMhs.getIncreasingId(userHp);
        }
        userMhs.insert(userHp, null, null);
        //endregion

        return reObj;
    }

    @Override
    public ResultObj<Boolean> checkUser(String userName) {
        ResultObj<Boolean> reObj = new ResultObj<Boolean>(true);
        EntityHelper<FaUserEntity> eh = new EntityHelper(FaUserEntity.class);
        int ent = userMhs.count(eh, x -> x.loginName == userName);
        reObj.data = ent > 0;
        reObj.success = true;
        return reObj;
    }

    @Override
    public ResultObj<Boolean> checkLogin(String userName) {
        ResultObj<Boolean> reObj = new ResultObj<Boolean>(true);
        EntityHelper<FaLoginEntity> eh= new EntityHelper(FaLoginEntity.class);
        int ent = loginMhs.count(eh,x -> x.loginName == userName);
        reObj.data = ent > 0;
        reObj.success = true;
        return reObj;
    }

    @Transactional(rollbackFor=Exception.class)
    public Result deleteUser(String userName) {
        Result reObj = new Result(true);

            EntityHelper<FaUserEntity> eh= new EntityHelper(FaUserEntity.class);

            int ent =userMhs.delete(eh,x -> x.loginName == userName);
            reObj = deleteLogin(userName);

            reObj.success=true;

        return reObj;
    }

    @Override
    public Result deleteLogin(String userName) {
        Result reObj = new Result(true);
        EntityHelper<FaLoginEntity> eh= new EntityHelper(FaLoginEntity.class);

        int ent = loginMhs.delete(eh,x -> x.loginName == userName);
        reObj.success = ent > 0;
        return reObj;
    }

    /**
     * 用户登录
     * @param inEnt
     * @return
     */
    public ResultObj<FaUserEntity> userLogin(LogingDto inEnt) {
        ResultObj<FaUserEntity> reObj = getSingleByPwd(inEnt);
        if(reObj.success){
            reObj.msg = "登录成功";
            TokenUser tu = new TokenUser();
            tu.setName(inEnt.userName);
            tu.setPassword(inEnt.password);
            String token = TokenUtil.createTokenForUser(tu);
            reObj.code = token;
        }
        return reObj;
    }

    @Override
    public ResultObj<FaUserEntity> getSingleByPwd(LogingDto inEnt) {
        ResultObj<FaUserEntity> reObj = new ResultObj<>();
        String loginName = inEnt.loginName;
        FaLoginEntity ent = loginMhs.getSingle(new EntityHelper(FaLoginEntity.class), x -> x.loginName == loginName);

        if (ent == null) {
            return new ResultObj<>(false, "账号不存在");
        } else {
            if (!ent.password.equals(StringHelper.getMD5(inEnt.password))) {
                return new ResultObj<>(false, "密码有误");
            }

            if (ent.isLocked != 0) {
                return new ResultObj<>(false, "账号状态有误");
            }

            FaUserEntity user = userMhs.getSingle(new EntityHelper(FaUserEntity.class), y -> y.loginName == loginName);

            if (user == null) {
                return new ResultObj<>(false, "用户不存在");
            }

            if (user.isLocked != 0) {
                return new ResultObj<>(false, "用户状态有误");
            }

            reObj.data = user;
            reObj.success = true;
            return reObj;
        }
    }

    public Result resetPassword(ResetPasswordDto inEnt) {
        Result reObj=new Result();
        EntityHelper<FaLoginEntity> loginEh = new EntityHelper<>(new FaLoginEntity());
        loginEh.data.loginName=inEnt.loginName;
        loginEh.data.password=StringHelper.getMD5(inEnt.newPwd);
        loginEh.data.verifyCode=inEnt.verifyCode;

        String loginName=inEnt.loginName;
        FaLoginEntity single=loginMhs.getSingle(loginEh,x->x.loginName==loginName);
        if(single==null){
            reObj.success=false;
            reObj.msg="用户不存在";
            return reObj;
        }
        if(single.verifyCode!=null && !single.verifyCode.equals(inEnt.verifyCode)){
            reObj.success=false;
            reObj.msg="验证码有误";
            return reObj;
        }

        List<String> saveFieldList= Arrays.asList("password");
        List<String> whereList= Arrays.asList("loginName");
        int opNum= loginMhs.update(loginEh,saveFieldList,whereList);
        reObj.success=opNum>0;
        reObj.msg="更新成功";
        return reObj;
    }

    public ResultObj<Boolean> userEditPwd(EditPwdDto inEnt) {
        ResultObj<Boolean> reObj= new ResultObj<>();
        ResultObj<FaUserEntity> userResult = getSingleByPwd(new LogingDto(inEnt.loginName,inEnt.oldPwd){});
        if(userResult.success){

            EntityHelper<FaLoginEntity> loginEh = new EntityHelper<>(new FaLoginEntity());
            loginEh.data.loginName=inEnt.loginName;
            loginEh.data.password=StringHelper.getMD5(inEnt.newPwd);

            List<String> saveFieldList= Arrays.asList("password");
            List<String> whereList= Arrays.asList("loginName");
            int opNum= loginMhs.update(loginEh,saveFieldList,whereList);
            reObj.data=opNum>0;
            reObj.success=true;
            reObj.msg="更新成功";
            return reObj;
        }
        return reObj;
    }

    @Override
    @Transactional
    public Result userEditLoginName(String oldName, String newName) {
        Result resultObj= new Result();
        ResultObj<Boolean> checkObj=checkLogin(newName);
        //region 检测是否存在
        //表示存在
        if(checkObj.data){
            resultObj.success=false;
            resultObj.msg="账号已经存在";
            return  resultObj;
        }

        checkObj=checkUser(newName);
        //表示存在
        if(checkObj.data){
            resultObj.success=false;
            resultObj.msg="用户已经存在";
            return  resultObj;
        }
        //endregion

        //region 修改账号
        EntityHelper<FaLoginEntity> loginEh = new EntityHelper<>(new FaLoginEntity());
        loginEh.data.loginName=newName;
        List<String> saveFieldList= Arrays.asList("loginName");
        int opNum= loginMhs.update(loginEh,saveFieldList," LOGIN_NAME = '"+oldName+"'");

        if(opNum<1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            resultObj.success=false;
            resultObj.msg="修改账号失败";
            return resultObj;
        }
        //endregion

        //region 修改用户
        EntityHelper<FaUserEntity> userEh = new EntityHelper<>(new FaUserEntity());
        userEh.data.loginName=newName;
        opNum=userMhs.update(userEh,saveFieldList," LOGIN_NAME = '"+oldName+"'");
        if(opNum<1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            resultObj.success = false;
            resultObj.msg = "修改用户失败";
            return resultObj;
        }
        //endregion
        resultObj.success=true;
        resultObj.msg="更新成功";
        return resultObj;
    }

    public Result userEditUser(DtoSave<FaUserEntity> inDto) {
        Result resultObj= new Result();

        EntityHelper<FaUserEntity> userEh = new EntityHelper<>(new FaUserEntity());
        int opNum=userMhs.update(userEh,inDto.saveFieldList,inDto.whereList);
        if(opNum<1) {
            resultObj.success = false;
            resultObj.msg = "修改用户失败";
            return resultObj;
        }
        return resultObj;
    }
}
