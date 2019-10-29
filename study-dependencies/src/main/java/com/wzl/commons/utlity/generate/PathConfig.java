package com.wzl.commons.utlity.generate;

import cn.hutool.core.convert.Convert;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
     * 实体路径
     */
    public String entityPath;

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

    /**
     * 字段字符串
     */
    public String clumStr;

    /**
     * 表名备注
     */
    public String tableNameRmark;

    /**
     * 生成文件数，用于生成文件
     */
    public List<Integer> makeFileNum=Arrays.asList(1,2,3,4,5,6,7,8);


    public String getConsumerControllerInterfaceText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
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

        contentStr = String.format(contentStr, packageName, tableName);
        return contentStr;
    }

    public String getConsumerControllerImplText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
                "\n" +
                "import cn.hutool.core.convert.Convert;\n" +
                "import %4$s.controller.%2$sController;\n" +
                "import %4$s.feign.%2$sService;\n" +
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
                "        return service.singleByKey(inObj);\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"delete\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"删除角色\")\n" +
                "    public Result delete(@RequestBody DtoDo inObj) {\n" +
                "        return service.delete(inObj);\n" +
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

        contentStr = String.format(contentStr, packageName, tableName, tableName.toLowerCase(),this.consumerPackageName);
        return contentStr;
    }

    public String getConsumerFeignInterfaceText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
                "\n" +
                "import %1$s.impl.%2$sServiceImpl;\n" +
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
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%3$s/singleByKey\")\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(@RequestBody DtoDo inEnt);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"/%3$s/delete\")\n" +
                "    Result delete(@RequestBody DtoDo inEnt);\n" +
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

        contentStr = String.format(contentStr, packageName, tableName, tableName.toLowerCase());
        return contentStr;
    }

    public String getConsumerFeignImplText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
                "\n" +
                "import %3$s.feign.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "@Service\n" +
                "public class %2$sServiceImpl implements %2$sService {\n" +
                "    @Override\n" +
                "    public ResultObj<Fa%2$sEntity> singleByKey(DtoDo inEnt) {\n" +
                "        ResultObj<Fa%2$sEntity>  reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result delete(DtoDo inEnt) {\n" +
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

        contentStr = String.format(contentStr, packageName, tableName,this.consumerPackageName);
        return contentStr;
    }

    public String getProviderControllerInterfaceText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
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
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(@RequestBody DtoDo inEnt);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    Result delete(@RequestBody DtoDo inEnt);\n" +
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

        contentStr = String.format(contentStr, packageName, tableName);
        return contentStr;
    }

    public String getProviderControllerImplText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
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
                "    public ResultObj<Fa%2$sEntity> singleByKey(@RequestBody DtoDo inEnt) {\n" +
                "        return service.singleByKey(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    @ApiOperation(value=\"删除%2$s对象\")\n" +
                "    @RequestMapping(value = \"delete\", method = RequestMethod.POST)\n" +
                "    public Result delete(@RequestBody DtoDo inEnt) {\n" +
                "        return service.delete(inEnt);\n" +
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

        contentStr = String.format(contentStr, packageName, tableName, tableName.toLowerCase());
        return contentStr;
    }

    public String getProviderServerInterFaceText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
                "\n" +
                "\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "\n" +
                "public interface %2$sService {\n" +
                "    //region 基本方法\n" +
                "    /**\n" +
                "     * 查询单条\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    ResultObj<Fa%2$sEntity> singleByKey(DtoDo inEnt);\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    Result delete(DtoDo inEnt);\n" +
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

        contentStr = String.format(contentStr, packageName, tableName);
        return contentStr;
    }

    public String getProviderServerImplText(String packageName, String tableName) {
        String contentStr = "package %1$s;\n" +
                "\n" +
                "import com.dependencies.mybatis.service.MyBatisService;\n" +
                "import %1$s.%2$sService;\n" +
                "import com.wzl.commons.model.*;\n" +
                "import com.wzl.commons.model.dto.DtoSave;\n" +
                "import com.wzl.commons.model.entity.Fa%2$sEntity;\n" +
                "import com.wzl.commons.model.mynum.DatabaseGeneratedOption;\n" +
                "import com.wzl.commons.retention.EntityHelper;\n" +
                "import org.apache.commons.lang3.StringUtils;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import cn.hutool.core.convert.Convert;\n" +
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
                "    public ResultObj<Fa%2$sEntity> singleByKey(DtoDo inEnt) {\n" +
                "        ResultObj<Fa%2$sEntity> reObj=new ResultObj<>(true);\n" +
                "        reObj.data=dapper.getSingleByPrimaryKey(eh, Convert.toInt(inEnt.key));\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result delete(DtoDo inEnt) {\n" +
                "        Result reObj = new Result();\n" +
                "        Integer key = Convert.toInt(inEnt.key);\n" +
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
                "            if(inEnt.whereList==null || inEnt.whereList.size()==0){\n" +
                "                inEnt.whereList = Arrays.asList(\"id\");\n" +
                "            }\n" +
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

        contentStr = String.format(contentStr, packageName, tableName);
        return contentStr;
    }


    public String getFunConsumerControllerInterfaceText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(@RequestBody %3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg);
        return contentStr;
    }

    public String getFunConsumerControllerImplText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    @RequestMapping(value = \"%1$s\", method = RequestMethod.POST)\n" +
                "    @ApiOperation(value = \"%4$s\")\n" +
                "    public %2$s %1$s(@RequestBody %3$s inEnt) {\n" +
                "        return service.%1$s(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg);
        return contentStr;
    }

    public String getFunConsumerFeignInterfaceText(String funName, String reObjStr, String inObj, String msg, String tableName) {
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

    public String getFunConsumerFeignImplText(String funName, String reObjStr, String inObj) {
        String contentStr = "" +
                "    public %2$s %1$s(%3$s inEnt) {\n" +
                "        %2$s reObj=new %2$s ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"网络有问题\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj);
        return contentStr;
    }

    public String getFunProviderControllerInterfaceText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(@RequestBody %3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg);
        return contentStr;
    }

    public String getFunProviderControllerImplText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    @ApiOperation(value=\"%4$s\")\n" +
                "    @RequestMapping(value = \"%1$s\", method = RequestMethod.POST)\n" +
                "    public %2$s %1$s(@RequestBody %3$s inEnt) {\n" +
                "        return service.%1$s(inEnt);\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";
        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg);
        return contentStr;
    }

    public String getFunProviderServerInterFaceText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    /**\n" +
                "     * %4$s\n" +
                "     * @param inEnt\n" +
                "     * @return\n" +
                "     */\n" +
                "    %2$s %1$s(%3$s inEnt);\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj, msg);
        return contentStr;
    }

    public String getFunProviderServerImplText(String funName, String reObjStr, String inObj, String msg) {
        String contentStr = "" +
                "    public %2$s %1$s(%3$s inEnt) {\n" +
                "        %2$s reObj=new ResultObj<> ();\n" +
                "        reObj.success=false;\n" +
                "        reObj.msg=\"开发中...\";\n" +
                "        return reObj;\n" +
                "    }\n" +
                "\n" +
                "    //——代码分隔线——\n}";

        contentStr = String.format(contentStr, funName, reObjStr, inObj);
        return contentStr;
    }


    /**
     * 修改文件内容
     *
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
                line = new String(line.getBytes("ISO-8859-1"), "utf-8");
                long ponit = raf.getFilePointer();
                if (line.contains(oldstr)) {
                    String str = line.replace(oldstr, newStr);
                    raf.seek(lastPoint);
//                    raf.writeBytes(str);
                    raf.write(str.getBytes("utf-8"));
                    ponit = lastPoint + str.length()+line.length();
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

    /**
     * 生成实体类字符串
     *
     * @return
     */
    public String makeEntity() {
        List<Filed> allFiled;
        if(!StringUtils.isAnyBlank(this.clumStr)){
            allFiled = getFiledList(this.clumStr);
        }
        else {
            allFiled = getFiledListByMysql(this.tableName);
        }
        StringBuffer attributeStr = new StringBuffer();
        for (Filed filed : allFiled) {
            StringBuffer sb = new StringBuffer();
            sb.append(String.format("\n" +
                    "    /**\n" +
                    "    * %1$s\n" +
                    "    */\n", filed.remark));
            if (filed.isKey) {
                sb.append("    @Key\n");
                sb.append("    @DatabaseGenerated(DatabaseGeneratedOption.Computed)\n");
                sb.append("    @Required\n");
            } else if (filed.required) {
                sb.append("    @Required\n");
            }
            sb.append(String.format("    @Display(Name = \"%1$s\")\n", filed.remark));
            sb.append(String.format("    @Column(\"%1$s\")\n", filed.name));
            sb.append(String.format("    public %1$s %2$s;\n", filed.type, makeCamelName(filed.name.toLowerCase(),false)));
            attributeStr.append(sb);
        }

        String classStr = "package com.wzl.commons.model.entity;\n" +
                "\n" +
                "import com.wzl.commons.model.mynum.DatabaseGeneratedOption;\n" +
                "import com.wzl.commons.retention.*;\n" +
                "\n" +
                "import java.util.Date;\n" +
                "\n" +
                "/**\n" +
                " * %1$s\n" +
                " */\n" +
                "@Table(\"%2$s\")\n" +
                "public class %4$sEntity {\n" +
                "\n" +
                "%3$s" +
                "}\n" +
                "/*\n" +
                "%5$s\n" +
                "*/\n" +
                "\n" +
                "\n" +
                "/*\n" +
                "%6$s\n" +
                "*/\n" +
                "";
        classStr = String.format(classStr, this.tableNameRmark, this.tableName, attributeStr, this.makeCamelName(this.tableName,true),makeAllSql(allFiled),makeAllQueryCfg(allFiled));
        return classStr;
    }

    /**
     * 生成SQL
     * @param allFiled
     * @return
     */
    private String makeAllSql(List<Filed> allFiled){
        String sql="" +
                "select \n" +
                "%1$s \n" +
                "from %2$s";
        String allListStr= String.join(",\n",allFiled.stream().map(x->"  "+x.name+" "+ makeCamelName(x.name,false)).collect(Collectors.toList()));
        sql=String.format(sql,allListStr,this.tableName);
        return sql;
    }

    private String makeAllQueryCfg(List<Filed> allFiled){
        String itemCfg="" +
                "  \"%1$s\": {\n" +
                "    \"title\": \"%2$s\",\n" +
                "    \"type\": \"%3$s\",\n" +
                "    \"editable\": true\n" +
                "  }";
        String allCfgListStr= String.join(",\n",allFiled.stream().map(x->String.format(itemCfg,makeCamelName(x.name,false),x.remark,x.type)).collect(Collectors.toList()));

        String reStr="" +
                "{\n" +
                "%1$s\n" +
                "}";

        reStr=String.format(reStr,allCfgListStr);
        return reStr;
    }

    /**
     * 将输入项转换成列表
     * @param clumContent PowerDesigner的行列信息
     * @return
     */
    private  List<Filed> getFiledList(String clumContent){
        List<Filed> allFiled = new ArrayList<>();
        if (StringUtils.isAllBlank(clumContent)) {
            return allFiled;
        }
        String[] rowsArr = clumContent.split("\n");
        for (String row : rowsArr) {
            if (!StringUtils.isAllBlank(row)) {
                String[] filedArr = row.split("\t");
                if (filedArr.length > 3) {
                    Filed filed = new Filed();
                    filed.name = filedArr[1];
                    filed.remark = filedArr[0];
                    filed.type = getFiledType(filedArr[2]);
                    filed.size = getFiledLength(filedArr[2]);
                    filed.isKey = Convert.toBool(filedArr[5]);
                    filed.required = Convert.toBool(filedArr[7]);
                    allFiled.add(filed);
                }
            }
        }
        return allFiled;
    }

    /**
     * 获取mysql的表结构
     * @param tableName
     * @return
     */
    private  List<Filed> getFiledListByMysql(String tableName){
        List<Filed> allFiled = new ArrayList<>();
        try {
            String sql="select column_name name, column_comment remark,COLUMN_TYPE type,IS_NULLABLE required,COLUMN_KEY='PRI' isKey  from information_schema.columns where table_schema ='study' and table_name = '%1$s' ;";
            sql=String.format(sql,tableName);
            List<Entity> allFiled1= Db.use().query( sql);
            for (Entity entity : allFiled1) {
                Filed tmp=new Filed();
                tmp.isKey=entity.getBool("isKey");
                tmp.name=entity.getStr("name");
                tmp.remark=entity.getStr("remark");
                if(StringUtils.isAnyBlank(tmp.remark)){
                    tmp.remark=tmp.name;
                }
                tmp.required=entity.getBool("required");
                tmp.type=getFiledType(entity.getStr("type"));
                tmp.size = getFiledLength(entity.getStr("type"));

                allFiled.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFiled;
    }

    private String getFiledType(String typeStr) {
        String reObj = "";
        typeStr=typeStr.split("\\(")[0];
        switch (typeStr) {
            case "int":
                reObj = "int";
                break;
            case "decimal":
                reObj = "BigDecimal";
                break;
            case "datetime":
                reObj = "Date";
                break;
            default:
                reObj = "String";
                break;
        }
        return reObj;
    }

    private int getFiledLength(String typeStr) {
        int reObj = 0;
        if (!StringUtils.isAllBlank(typeStr)) {
            String tmpStr = typeStr.replace("(", ",").replace(")", ",");
            String[] typeSplit = typeStr.split(",");
            if (typeSplit.length > 2) {
                reObj = Convert.toInt(typeSplit[1], 0);
            }
        }
        return reObj;
    }

    /**
     * 生成骆峰命名
     * @param name 需转换的名字
     * @param fristUper 首字母是否大写
     * @return
     */
    public String makeCamelName(String name,Boolean fristUper) {
        String reObj = "";
        if (StringUtils.isAllBlank(name)) {
            return reObj;
        }

        List<String> nameArr = new ArrayList<>(Arrays.asList(name.split("_")));
        nameArr = nameArr.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
        nameArr = nameArr.stream().map(x -> x.substring(0, 1).toUpperCase() + x.substring(1)).collect(Collectors.toList());
        reObj = String.join("", nameArr);
        if (fristUper) {
            reObj = reObj.substring(0, 1).toUpperCase() + reObj.substring(1);
        }
        else {
            reObj = reObj.substring(0, 1).toLowerCase() + reObj.substring(1);
        }
        return reObj;
    }
}
