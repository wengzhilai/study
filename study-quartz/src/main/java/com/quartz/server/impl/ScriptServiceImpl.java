package com.quartz.server.impl;

import com.dependencies.mybatis.service.MyBatisService;
import com.quartz.server.ScriptService;
import com.wzl.commons.model.*;
import com.wzl.commons.model.dto.DtoSave;
import com.wzl.commons.model.entity.FaScriptEntity;
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
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    MyBatisService<FaScriptEntity> dapper;

    EntityHelper<FaScriptEntity> eh = new EntityHelper<>(new FaScriptEntity());

    @Override
    public ResultObj<FaScriptEntity> singleByKey(DtoDo inEnt) {
        ResultObj<FaScriptEntity> reObj=new ResultObj<>(true);
        reObj.data=dapper.getSingleByPrimaryKey(eh, Convert.toInt(inEnt.key));
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
    public ResultObj<Integer> save(DtoSave<FaScriptEntity> inEnt) {
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
        }
        resultObj.success = resultObj.data > 0;

        return resultObj;
    }


    @Override
    public List<FaScriptEntity> getNormalScript() {
        List<FaScriptEntity> reList= dapper.getAll(eh,x->x.status=="正常");
        reList=reList.stream().filter(x->!StringUtils.isAllBlank(x.runWhen)).collect(Collectors.toList());
        return  reList;
    }

    //——代码分隔线——

}
