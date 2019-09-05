package com.wjbjp.server;

import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wjbjp.model.entity.FaUserEntity;
import com.wzl.commons.model.dto.EditPwdDto;
import com.wzl.commons.model.dto.LogingDto;
import com.wzl.commons.model.dto.ResetPasswordDto;

public interface LoginService {

    /**
     * 注册账号
     * 1、添加登录工号
     * 2、添加用户
     * @param inEnt
     * @return
     */
    ResultObj<Integer> loginReg(LogingDto inEnt);

    /**
     * 检测用户是否存在
     * @param userName
     * @return
     */
    ResultObj<Boolean> checkUser(String userName);

    /**
     * 检测账号是否存在
     * @param userName
     * @return
     */
    ResultObj<Boolean> checkLogin(String userName);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    Result deleteUser(String userName);

    /**
     * 删除账号
     * @param userName
     * @return
     */
    Result deleteLogin(String userName);

    /**
    * 用户登录
    * 只验证用户账号
    */

    ResultObj<FaUserEntity> userLogin(LogingDto inEnt);

    /**
     * 根据账号和密码获取账号
     * @param inEnt
     * @return
     */
    ResultObj<FaUserEntity> getSingleByPwd(LogingDto inEnt);

    /**
    * 重置用户密码
    */
    Result resetPassword(ResetPasswordDto inEnt);

    /**
    * 修改用户密码
    */
    ResultObj<Boolean> userEditPwd(EditPwdDto inEnt);

    /**
     * 修改用户账号
     * @param oldName 旧密码
     * @param newName 新密码
     * @return
     */
    Result userEditLoginName(String oldName,String newName);

    /**
     * 修改用户
     * @param inDto
     * @return
     */
    Result userEditUser(DtoSave<FaUserEntity> inDto);

}
