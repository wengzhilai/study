package com.quartz.server.impl;

import cn.hutool.crypto.SecureUtil;
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
    public FaScriptEntity singleByKey(int key) {
        return dapper.getSingleByPrimaryKey(eh, key);
    }

    @Override
    public Result delete(DtoDo inEnt) {
        Result reObj = new Result();
        Integer key = Convert.toInt(inEnt.key);
        dapper.alter("DELETE from fa_script_task_log where SCRIPT_TASK_ID in (select id from fa_script_task where SCRIPT_ID ="+key+"2)");
        dapper.alter("DELETE from fa_script_task where SCRIPT_ID ="+key);
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
                if(inEnt.saveFieldList!=null){
                    inEnt.saveFieldList.add("id");
                }
            }
            try {
                eh.updateNullValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            eh.data.bodyHash= SecureUtil.md5(eh.data.bodyText);
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
        List<FaScriptEntity> reList;
        reList= dapper.getAll(eh,x->x.status=="1");
        reList=reList.stream().filter(x->!StringUtils.isAllBlank(x.runWhen)).collect(Collectors.toList());
        return  reList;
    }

    //——代码分隔线——

}
