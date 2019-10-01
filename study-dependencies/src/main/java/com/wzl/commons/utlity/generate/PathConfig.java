package com.wzl.commons.utlity.generate;

import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.io.IOException;
import java.io.RandomAccessFile;

@Data
public class PathConfig {
    /**
     * 消费者控制层路径
     */
    public String consumerPath;


    /**
     * 生产者路径
     */
    public String providerPath;

    /**
     * 表名
     */
    public String tableName;
    /**
     * 消费费包名
     */
    public String consumerPackageName;
    /**
     * 提供者包名
     */
    public String providerPackageName;



    public String getConsumerControllerInterfaceText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "\n" +
                "public interface %2$sController {\n" +
                "    //region 基本方法\n" +
                "    /**\n" +
                "     * 查询单条\n" +
                "     * @param inObj\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(@RequestBody DtoDo inObj);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param inObj\n" +
                "     * @return\n" +
                "     */\n" +
                "    Result delete(@RequestBody DtoDo inObj);\n" +
                "\n" +
                "    /**\n" +
                "     * 保存基本信息\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Integer> save(@RequestBody DtoSave<Fa%2$sEntity> inEnt);\n" +
                "    //endregion\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName);
        return  contentStr;
    }

    public String getConsumerControllerImplText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "import cn.hutool.core.convert.Convert;\n" +
                "import com.user.consumer.controller.%2$sController;\n" +
                "import com.user.consumer.feign.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import io.swagger.annotations.ApiOperation;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "@RestController\n" +
                "@RequestMapping(\"%3$s\")\n" +
                "public class %2$sControllerImpl implements %2$sController {\n" +
                "\n" +
                "    @Autowired\n" +
                "    %2$sService service;\n" +
                "\n" +
                "    @RequestMapping(value = \"singleByKey\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"查询单个角色\")\n" +
                "    public ResultObj<Fa%2$sEntity> singleByKey(@RequestBody DtoDo inObj) {\n" +
                "        return service.singleByKey(Convert.toInt(inObj.key));\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"delete\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"删除角色\")\n" +
                "    public Result delete(@RequestBody DtoDo inObj) {\n" +
                "        return service.delete(Convert.toInt(inObj.key));\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"save\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"保存角色\")\n" +
                "    public ResultObj<Integer> save(@RequestBody DtoSave<Fa%2$sEntity> inEnt) {\n" +
                "        return service.save(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName,tableName.toLowerCase());
        return  contentStr;
    }

    public String getConsumerFeignInterfaceText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "import %1$s.impl.RoleServiceImpl;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import org.springframework.cloud.openfeign.FeignClient;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "\n" +
                "@FeignClient(value = \"user-provider-%3$s\",url = \"http://localhost:9001\",fallback = %2$sServiceImpl.class)\n" +
                "public interface %2$sService {\n" +
                "    //region 基本方法\n" +
                "    /**\n" +
                "     * 查询单条\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%3$s/singleByKey\")\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(@RequestBody int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%3$s/delete\")\n" +
                "    Result delete(@RequestBody int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 保存基本信息\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%3$s/save\")\n" +
                "    ResultObj<Integer> save(@RequestBody DtoSave<Fa%2$sEntity> inEnt);\n" +
                "    //endregion\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName,tableName.toLowerCase());
        return  contentStr;
    }

    public String getConsumerFeignImplText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "import com.user.consumer.feign.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "@Service\n" +
                "public class %2$sServiceImpl implements %2$sService {\n" +
                "    @Override\n" +
                "    public ResultObj<Fa%2$sEntity> singleByKey(int key) {\n" +
                "        ResultObj<Fa%2$sEntity>  reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result delete(int key) {\n" +
                "        Result reObj=new Result();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ResultObj<Integer> save(DtoSave<Fa%2$sEntity> inEnt) {\n" +
                "        ResultObj<Integer>  reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName);
        return  contentStr;
    }

    public String getProviderControllerInterfaceText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "\n" +
                "public interface %2$sController {\n" +
                "    //region 基本方法\n" +
                "    /**\n" +
                "     * 查询单条\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(@RequestBody int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    Result delete(@RequestBody int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 保存基本信息\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Integer> save(@RequestBody DtoSave<Fa%2$sEntity> inEnt);\n" +
                "    //endregion\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName);
        return  contentStr;
    }

    public String getProviderControllerImplText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "import com.user.provider.controller.%2$sController;\n" +
                "import com.user.provider.server.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import io.swagger.annotations.ApiOperation;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "@RestController\n" +
                "@RequestMapping(\"%3$s\")\n" +
                "public class %2$sControllerImpl implements %2$sController {\n" +
                "    @Autowired\n" +
                "    %2$sService service;\n" +
                "\n" +
                "    @ApiOperation(value=\"获取%2$s对象\")\n" +
                "    @RequestMapping(value = \"singleByKey\", method = RequestMethod.POST)\n" +
                "    public ResultObj<Fa%2$sEntity> singleByKey(@RequestBody int key) {\n" +
                "        return service.singleByKey(key);\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value=\"删除%2$s对象\")\n" +
                "    @RequestMapping(value = \"delete\", method = RequestMethod.POST)\n" +
                "    public Result delete(@RequestBody int key) {\n" +
                "        return service.delete(key);\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value=\"删除%2$s对象\")\n" +
                "    @RequestMapping(value = \"save\", method = RequestMethod.POST)\n" +
                "    public ResultObj<Integer> save(@RequestBody DtoSave<Fa%2$sEntity> inEnt) {\n" +
                "        return service.save(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName,tableName.toLowerCase());
        return  contentStr;
    }

    public String getProviderServerInterFaceText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DataGridDataJson;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.dto.query.QuerySearchDto;\n" +
                "import com.wzl.commons.model.entity.FaQueryEntity;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "\n" +
                "public interface %2$sService {\n" +
                "    //region 基本方法\n" +
                "    /**\n" +
                "     * 查询单条\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param key\n" +
                "     * @return\n" +
                "     */\n" +
                "    Result delete(int key);\n" +
                "\n" +
                "    /**\n" +
                "     * 保存基本信息\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Integer> save(DtoSave<Fa%2$sEntity> inEnt);\n" +
                "    //endregion\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName);
        return  contentStr;
    }

    public String getProviderServerImplText(String packageName,String tableName){
        String contentStr= "package %1$s;\n" +
                "\n" +
                "import com.dependencies.mybatis.service.MyBatisService;\n" +
                "import com.user.provider.server.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import com.wzl.commons.model.mynum.DatabaseGeneratedOption;\n" +
                "import com.wzl.commons.retention.EntityHelper;\n" +
                "import org.apache.commons.lang3.StringUtils;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Collectors;\n" +
                "\n" +
                "@Service\n" +
                "public class %2$sServiceImpl implements %2$sService {\n" +
                "    @Autowired\n" +
                "    MyBatisService<Fa%2$sEntity> dapper;\n" +
                "\n" +
                "    EntityHelper<Fa%2$sEntity> eh = new EntityHelper<>(new Fa%2$sEntity());\n" +
                "\n" +
                "    @Override\n" +
                "    public ResultObj<Fa%2$sEntity> singleByKey(int key) {\n" +
                "        ResultObj<Fa%2$sEntity> reObj=new ResultObj<>(true);\n" +
                "        reObj.data=dapper.getSingleByPrimaryKey(eh, key);\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result delete(int key) {\n" +
                "        Result reObj = new Result();\n" +
                "\n" +
                "        reObj.success = dapper.delete(eh, x -> x.id == key) > 0;\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ResultObj<Integer> save(DtoSave<Fa%2$sEntity> inEnt) {\n" +
                "        ResultObj<Integer> resultObj = new ResultObj<>();\n" +
                "        eh.data = inEnt.data;\n" +
                "        if(inEnt.whereList==null || inEnt.whereList.size()==0){\n" +
                "            inEnt.whereList=new ArrayList<>();\n" +
                "            inEnt.whereList.add(\"id\");\n" +
                "        }\n" +
                "\n" +
                "        if (inEnt.data.id == 0) {\n" +
                "            if (eh.dbKeyType == DatabaseGeneratedOption.Computed) {\n" +
                "                eh.data.id = dapper.getIncreasingId(eh);\n" +
                "                inEnt.saveFieldList.add(\"id\");\n" +
                "            }\n" +
                "            resultObj.data = dapper.insert(eh, inEnt.saveFieldList, null);\n" +
                "        } else {\n" +
                "            resultObj.data = dapper.update(eh, inEnt.saveFieldList, inEnt.whereList);\n" +
                "        }\n" +
                "        resultObj.success = resultObj.data > 0;\n" +
                "\n" +
                "        return resultObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n" +
                "\n" +
                "}\n";

        contentStr=String.format(contentStr,packageName,tableName);
        return  contentStr;
    }





    public String getFunConsumerControllerInterfaceText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(@RequestBody %3$s inEnt);\n" +
                "    //endregion\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj,msg);
        return  contentStr;
    }

    public String getFunConsumerControllerImplText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    @RequestMapping(value = \"%1$s\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"%4$s\")\n" +
                "    public %2$s %1$s(@RequestBody %3$s inEnt) {\n" +
                "        return service.%1$s(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj,msg);
        return  contentStr;
    }

    public String getFunConsumerFeignInterfaceText(String  funName,String reObjStr,String inObj,String msg,String tableName) {
        String contentStr = "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%5$s/%1$s\")\n" +
                "    %2$s %1$s(@RequestBody %3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg, tableName.toLowerCase());
        return contentStr;
    }

    public String getFunConsumerFeignImplText(String  funName,String reObjStr,String inObj){
        String contentStr= "" +
                "    public %2$s %1$s(%3$s inEnt) {\n" +
                "        %2$s reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj);
        return  contentStr;
    }

    public String getFunProviderControllerInterfaceText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(@RequestBody %3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj,msg);
        return  contentStr;
    }

    public String getFunProviderControllerImplText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    @ApiOperation(value=\"%4$s\")\n" +
                "    @RequestMapping(value = \"%1$s\", method = RequestMethod.POST)\n" +
                "    public %2$s %1$s(@RequestBody %3$s inEnt) {\n" +
                "        return service.%1$s(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";
        contentStr=String.format(contentStr,funName,reObjStr,inObj,msg);
        return  contentStr;
    }

    public String getFunProviderServerInterFaceText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(%3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj,msg);
        return  contentStr;
    }

    public String getFunProviderServerImplText(String  funName,String reObjStr,String inObj,String msg){
        String contentStr= "" +
                "    public %2$s %1$s(%3$s inEnt) {\n" +
                "        %2$s reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"开发中...\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr=String.format(contentStr,funName,reObjStr,inObj);
        return  contentStr;
    }


    /**
     * 修改文件内容
     * @param allfileName
     * @param oldstr
     * @param newStr
     * @return
     */
    public boolean modifyFileContent(String allfileName, String oldstr, String newStr) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(allfileName, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量

            while ((line = raf.readLine()) != null) {
                line=new String(line.getBytes("ISO-8859-1"),"utf-8");
                long ponit = raf.getFilePointer();
                if(line.contains(oldstr)){
                    String str=line.replace(oldstr, newStr);
                    raf.seek(lastPoint);
//                    raf.writeBytes(str);
                    raf.write(str.getBytes("utf-8"));
                    ponit=lastPoint+str.length();
                    raf.seek(ponit);

                }
                lastPoint = ponit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
