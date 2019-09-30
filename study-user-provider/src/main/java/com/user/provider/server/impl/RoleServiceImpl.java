package com.user.provider.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.user.provider.server.RoleService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaQueryEntity;
import com.wzl.commons.model.entity.FaRoleEntity;
import com.wzl.commons.model.entity.view.FaRoleModuleEntityView;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    MyBatisService<FaRoleEntity> dapper;

    @Autowired
    MyBatisService<FaRoleModuleEntityView> dapperRoleModule;

    EntityHelper<FaRoleEntity> eh = new EntityHelper<>(new FaRoleEntity());

    @Override
    public ResultObj<FaRoleEntity> singleByKey(int key) {
        ResultObj<FaRoleEntity> reObj=new ResultObj<>(true);
        reObj.data=dapper.getSingleByPrimaryKey(eh, key);
        List<FaRoleModuleEntityView> allList= dapperRoleModule.getAll(new EntityHelper<>(new FaRoleModuleEntityView()),x->x.roleId==key);
        reObj.data.moduleIdStr= allList.stream().map(x->x.moduleId).collect(Collectors.toList());
        return reObj;
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        dapper.alter("delete from fa_role_module where ROLE_ID = "+key);

        reObj.success = dapper.delete(eh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaRoleEntity> inEnt) {
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
            resultObj.data = dapper.update(eh, inEnt.saveFieldList, inEnt.whereList);
            dapper.alter("delete from fa_role_module where ROLE_ID = "+eh.data.id);
        }
        resultObj.success = resultObj.data > 0;

        dapper.alter(String.format("insert into fa_role_module(ROLE_ID,MODULE_ID) select %1$s ROLE_ID,ID MODULE_ID from fa_module where ID IN (%2$s) ", eh.data.id, StringUtils.join(inEnt.data.moduleIdStr,",")));

        return resultObj;
    }
}
