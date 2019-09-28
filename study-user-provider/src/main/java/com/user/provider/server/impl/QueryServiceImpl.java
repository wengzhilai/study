package com.user.provider.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.user.provider.model.entity.FaModuleEntity;
import com.user.provider.model.entity.FaQueryEntity;
import com.user.provider.model.entity.view.FaUserRoleEntityView;
import com.user.provider.server.QueryService;
import com.wzl.commons.model.Result;
import com.wzl.commons.model.ResultObj;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    MyBatisService<FaQueryEntity> dapper;

    EntityHelper<FaQueryEntity> moduleEh = new EntityHelper<>(new FaQueryEntity());

    @Override
    public FaQueryEntity singleByKey(int key) {
        moduleEh.data.id=key;
        return  dapper.getSingleByPrimaryKey(moduleEh,key);
    }

    @Override
    public Result delete(int key) {
        Result reObj = new Result();
        reObj.success = dapper.delete(moduleEh, x -> x.id == key) > 0;
        return reObj;
    }

    @Override
    public ResultObj<Integer> save(DtoSave<FaQueryEntity> inEnt) {
        ResultObj<Integer> resultObj= new ResultObj<>();
        moduleEh.data=inEnt.data;
        if(inEnt.data.id==0){
            if (moduleEh.data.id == 0 && moduleEh.dbKeyType == DatabaseGeneratedOption.Computed) {
                moduleEh.data.id = dapper.getIncreasingId(moduleEh);
            }
            resultObj.data= dapper.insert(moduleEh,inEnt.saveFieldList,null);
        }
        else {
            resultObj.data=dapper.update(moduleEh,inEnt.saveFieldList,inEnt.whereList);
            resultObj.success=resultObj.data>0;
        }
        return resultObj;
    }

    @Override
    public ResultObj<FaQueryEntity> GetSingleQuery(String code) {
        ResultObj<FaQueryEntity> resultObj= new ResultObj<>();

        resultObj.dataList=dapper.getAll(moduleEh,x->x.code==code);
        if(resultObj.dataList!=null && resultObj.dataList.size()>0){
            resultObj.data=resultObj.dataList.get(0);
        }
        return resultObj;
    }
}
