package com.user.provider.server.impl;

import cn.hutool.core.convert.Convert;
import com.dependencies.mybatis.service.MyBatisService;
import com.wzl.commons.model.entity.*;
import com.user.provider.server.ModuleService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.view.FaRoleModuleEntityView;
import com.wzl.commons.model.entity.view.FaUserRoleEntityView;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    MyBatisService<FaModuleEntity> moduleMhs;

    @Autowired
    MyBatisService<FaRoleModuleEntityView> dapperRoleModule;

    @Autowired
    MyBatisService<FaUserRoleEntityView> dapperUserRole;

    EntityHelper<FaModuleEntity> moduleEh = new EntityHelper<>(new FaModuleEntity());

    @Override
    public ResultObj<FaModuleEntity> singleByKey(int key) {
        moduleEh.data.id = key;
        ResultObj<FaModuleEntity> resultObj=new ResultObj<>(true);
        resultObj.data=moduleMhs.getSingleByPrimaryKey(moduleEh, key);
        return resultObj;
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = moduleMhs.delete(moduleEh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaModuleEntity> inEnt) {
        ResultObj<Integer> resultObj= new ResultObj<>(true);
        if(inEnt.data.parentId==0){
            inEnt.data.parentId=0;
        }
        moduleEh.data=inEnt.data;
        if(inEnt.data.id==0){
            if (moduleEh.data.id == 0 && moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = moduleMhs.getIncreasingId(moduleEh);
            }
            resultObj.data= moduleMhs.insert(moduleEh,null,null);
        }
        else {
            if(inEnt.whereList==null || inEnt.whereList.size()==0){
                inEnt.whereList=Arrays.asList("id");
            }
            resultObj.data=moduleMhs.update(moduleEh,inEnt.saveFieldList,inEnt.whereList);
            resultObj.success=resultObj.data>0;
        }
        return resultObj;
    }

    @Override
    public ResultObj<FaModuleEntity> getMenu() {
        ResultObj<FaModuleEntity> resultObj= new ResultObj<>();
        resultObj.dataList= moduleMhs.getAll(moduleEh,x->x.isHide==0,1,100,null);
        return resultObj;
    }

    @Override
    public ResultObj<FaModuleEntity> getMenuByRoleId(List<Integer> roleIdList) {
        return null;
    }

    @Override
    public ResultObj<FaModuleEntity> GetMGetMenuByUserId(int userId) {
        ResultObj<FaModuleEntity> resultObj=new ResultObj<>();
        List<FaUserRoleEntityView> roleList=dapperUserRole.getAll(
                new EntityHelper<>(new FaUserRoleEntityView()),
                x->x.userId==userId
                );

        List<String> roleIdList=roleList.stream().map(x->Convert.toStr(x.roleId)).collect(Collectors.toList());
        List<FaRoleModuleEntityView> allList= dapperRoleModule.getAll(new EntityHelper<>(new FaRoleModuleEntityView()),"a.ROLE_ID in ("+String.join(",",roleIdList)+")",1,100,null);
        Set<String> allModuleIdList= allList.stream().collect(Collectors.groupingBy(x->Convert.toStr(x.moduleId))).keySet();
        List<FaModuleEntity> allModule= moduleMhs.getAll(moduleEh,"ID in ("+String.join(",",allModuleIdList)+")");
        resultObj.dataList=getChildItems(allModule,0);
        resultObj.success=true;
        return  resultObj;
    }

    /**
     * 获取所有子项
     * @param inList
     * @param parentId
     * @return
     */
    private List<FaModuleEntity> getChildItems(List<FaModuleEntity> inList, Integer parentId)
    {
        List<FaModuleEntity> childList = inList.stream().filter(i -> i.parentId == parentId).collect(Collectors.toList());
        List<FaModuleEntity> reObj = new ArrayList<>();;
        for (FaModuleEntity item : childList) {
            item.children = getChildItems(inList, item.id);
            reObj.add(item);
        }
        return childList;
    }

    //——代码分隔线——

}
