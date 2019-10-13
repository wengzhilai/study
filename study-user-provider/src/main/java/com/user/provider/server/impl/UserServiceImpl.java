package com.user.provider.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.user.provider.server.UserService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaUserEntity;
import com.wzl.commons.model.entity.view.FaUserRoleEntityView;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.convert.Convert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MyBatisService<FaUserEntity> dapper;

    @Autowired
    MyBatisService<FaUserRoleEntityView> dapperUserRole;

    EntityHelper<FaUserEntity> eh = new EntityHelper<>(new FaUserEntity());

    @Override
    public ResultObj<FaUserEntity> singleByKey(DtoDo inEnt) {
        ResultObj<FaUserEntity> reObj=new ResultObj<>(true);
        Integer key=Convert.toInt(inEnt.key);
        reObj.data=dapper.getSingleByPrimaryKey(eh, key);
        List<FaUserRoleEntityView> userRoleList=dapperUserRole.getAll(new EntityHelper<>(new FaUserRoleEntityView()),x->x.userId==key);
        reObj.data.roleIdList=userRoleList.stream().map(x->x.roleId).collect(Collectors.toList());
        return reObj;
    }

    @Override
    public Result delete(DtoDo inEnt) {
        Result reObj = new Result();
        Integer key = Convert.toInt(inEnt.key);
        reObj.success = dapper.delete(eh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaUserEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();
        eh.data = inEnt.data;
        if(inEnt.whereList==null || inEnt.whereList.size()==0){
            inEnt.whereList=new ArrayList<>();
            inEnt.whereList.add("id");
        }

        if (inEnt.data.id == 0) {
            if (eh.dbKeyType == DatabaseGeneratedOption.Computed) {
                eh.data.id = dapper.getIncreasingId(eh);
                inEnt.saveFieldList.add("id");
            }
            resultObj.data = dapper.insert(eh, inEnt.saveFieldList, null);
        } else {
            if(inEnt.whereList==null || inEnt.whereList.size()==0){
                inEnt.whereList = Arrays.asList("id");
            }
            resultObj.data = dapper.update(eh, inEnt.saveFieldList, inEnt.whereList);
            dapper.alter("delete from fa_user_role where USER_ID = "+eh.data.id);
        }
        resultObj.success = resultObj.data > 0;
        dapper.alter(String.format("insert into fa_user_role(USER_ID,ROLE_ID) select %1$s USER_ID,ID ROLE_ID from fa_role where ID IN (%2$s) ", eh.data.id, StringUtils.join(inEnt.data.roleIdList,",")));

        return resultObj;
    }

    //——代码分隔线——

}
