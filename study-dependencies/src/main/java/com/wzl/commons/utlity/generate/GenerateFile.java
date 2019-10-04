package com.wzl.commons.utlity.generate;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;

public class GenerateFile {

    /**
     * 创建页面
     * @param cfg
     * @throws IOException
     */
    public static void Start(PathConfig cfg) throws IOException {

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"controller/"+cfg.tableName+"Controller.java");
            fw.write(cfg.getConsumerControllerInterfaceText(cfg.consumerPackageName + ".controller", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"controller/impl/"+cfg.tableName+"ControllerImpl.java");
            fw.write(cfg.getConsumerControllerImplText(cfg.consumerPackageName + ".controller.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"feign/"+cfg.tableName+"Service.java");
            fw.write(cfg.getConsumerFeignInterfaceText(cfg.consumerPackageName + ".feign", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"feign/impl/"+cfg.tableName+"ServiceImpl.java");
            fw.write(cfg.getConsumerFeignImplText(cfg.consumerPackageName + ".feign.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }



        {

            FileWriter fw = new FileWriter(cfg.providerPath+"controller/"+cfg.tableName+"Controller.java");
            fw.write(cfg.getProviderControllerInterfaceText(cfg.providerPackageName + ".controller", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"controller/impl/"+cfg.tableName+"ControllerImpl.java");
            fw.write(cfg.getProviderControllerImplText(cfg.providerPackageName + ".controller.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"server/"+cfg.tableName+"Service.java");
            fw.write(cfg.getProviderServerInterFaceText(cfg.providerPackageName + ".server", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"server/impl/"+cfg.tableName+"ServiceImpl.java");
            fw.write(cfg.getProviderServerImplText(cfg.providerPackageName + ".server.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

    }

    /**
     * 添加方法
     * @param cfg 配置
     * @param funName   表名
     * @param reObjStr 返回对象字符串
     * @param inObj 传入对象字符号昨晚上
     * @param msg 备注
     * @param tableName 表名
     * @throws IOException
     */
    public static void MakeNewFunction(PathConfig cfg,String  funName,String reObjStr,String inObj,String msg,String tableName) throws IOException {

        {
            String path=cfg.consumerPath+"controller/"+cfg.tableName+"Controller.java";
            String content=cfg.getFunConsumerControllerInterfaceText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {

            String path=cfg.consumerPath+"controller/impl/"+cfg.tableName+"ControllerImpl.java";
            String content=cfg.getFunConsumerControllerImplText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {
            String path=cfg.consumerPath+"feign/"+cfg.tableName+"Service.java";
            String content=cfg.getFunConsumerFeignInterfaceText(funName,reObjStr,inObj,msg,tableName);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {
            String path=cfg.consumerPath+"feign/impl/"+cfg.tableName+"ServiceImpl.java";
            String content=cfg.getFunConsumerFeignImplText(funName,reObjStr,inObj);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }



        {
            String path=cfg.providerPath+"controller/"+cfg.tableName+"Controller.java";
            String content=cfg.getFunProviderControllerInterfaceText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {
            String path=cfg.providerPath+"controller/impl/"+cfg.tableName+"ControllerImpl.java";
            String content=cfg.getFunProviderControllerImplText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {
            String path=cfg.providerPath+"server/"+cfg.tableName+"Service.java";
            String content=cfg.getFunProviderServerInterFaceText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

        {
            String path=cfg.providerPath+"server/impl/"+cfg.tableName+"ServiceImpl.java";
            String content=cfg.getFunProviderServerImplText(funName,reObjStr,inObj,msg);
            cfg.modifyFileContent(path,"    //——代码分隔线——",content);
        }

    }

    public static void MakeEntity(PathConfig cfg) throws IOException {

        FileWriter fw = new FileWriter(cfg.entityPath+ cfg.makeCamelName(cfg.tableName,true)+"Entity.java");
        fw.write(cfg.makeEntity());
        fw.flush();
        fw.close();
    }
}
