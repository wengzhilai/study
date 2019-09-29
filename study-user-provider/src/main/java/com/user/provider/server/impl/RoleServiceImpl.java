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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public FaRoleEntity singleByKey(int key) {
        FaRoleEntity reObj=dapper.getSingleByPrimaryKey(eh, key);
        List<FaRoleModuleEntityView> allList= dapperRoleModule.getAll(new EntityHelper<>(new FaRoleModuleEntityView()),x->x.roleId==key);
        reObj.moduleIdStr= allList.stream().map(x->x.moduleId).collect(Collectors.toList());
        return reObj;
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = dapper.delete(eh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaRoleEntity> inEnt) {
        ResultObj<Integer> resultObj = new ResultObj<>();
        eh.data = inEnt.data;
        if (inEnt.data.id == 0) {
            if (eh.data.id == 0 && eh.dbKeyType == DatabaseGeneratedOption.Computed) {
                eh.data.id = dapper.getIncreasingId(eh);
            }
            resultObj.data = dapper.insert(eh, inEnt.saveFieldList, null);
        } else {
            resultObj.data = dapper.update(eh, inEnt.saveFieldList, inEnt.whereList);
            resultObj.success = resultObj.data > 0;
        }
        return resultObj;
    }
}
