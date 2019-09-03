package com.wjbjp.server.impl;

import com.wjbjp.model.Result;
import com.wjbjp.model.ResultObj;
import com.wjbjp.model.dto.DtoSave;
import com.wjbjp.model.entity.FaModuleEntity;
import com.wjbjp.model.mynum.DatabaseGeneratedOption;
import com.wjbjp.retention.EntityHelper;
import com.wjbjp.server.MapperHelperService;
import com.wjbjp.server.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    MapperHelperService<FaModuleEntity> moduleMhs;

    EntityHelper<FaModuleEntity> moduleEh = new EntityHelper<>(new FaModuleEntity());

    @Override
    public FaModuleEntity singleByKey(int key) {
        moduleEh.data.id=key;
       return  moduleMhs.getSingleByPrimaryKey(moduleEh,key);
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = moduleMhs.delete(moduleEh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaModuleEntity> inEnt) {
        ResultObj<Integer> resultObj= new ResultObj<>();
        moduleEh.data=inEnt.data;
        if(inEnt.data.id==0){
            if (moduleEh.data.id == 0 && moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = moduleMhs.getIncreasingId(moduleEh);
            }
            resultObj.data= moduleMhs.insert(moduleEh,inEnt.saveFieldList,null);
        }
        else {
            resultObj.data=moduleMhs.update(moduleEh,inEnt.saveFieldList,inEnt.whereList);
            resultObj.success=resultObj.data>0;
        }
        return resultObj;
    }

    @Override
    public ResultObj<FaModuleEntity> getMenu() {
        ResultObj<FaModuleEntity> resultObj= new ResultObj<>();
        resultObj.dataList= moduleMhs.getAll(moduleEh,x->x.is_hide==0,1,100,null);
        return resultObj;
    }

    @Override
    public ResultObj<FaModuleEntity> getMenuByRoleId(List<Integer> roleIdList) {
        return null;
    }
}
