package com.wzl.commons.utlity.generate;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateFile {

    public static void Start(PathConfig cfg) throws IOException {

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"controller\\"+cfg.tableName+"Controller.java");
            fw.write(cfg.getConsumerControllerInterfaceText(cfg.consumerPackageName + ".controller", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"controller\\impl\\"+cfg.tableName+"ControllerImpl.java");
            fw.write(cfg.getConsumerControllerImplText(cfg.consumerPackageName + ".controller.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"feign\\"+cfg.tableName+"Service.java");
            fw.write(cfg.getConsumerFeignInterfaceText(cfg.consumerPackageName + ".feign", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.consumerPath+"feign\\impl\\"+cfg.tableName+"ServiceImpl.java");
            fw.write(cfg.getConsumerFeignImplText(cfg.consumerPackageName + ".feign.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }



        {

            FileWriter fw = new FileWriter(cfg.providerPath+"controller\\"+cfg.tableName+"Controller.java");
            fw.write(cfg.getProviderControllerInterfaceText(cfg.providerPackageName + ".controller", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"controller\\impl\\"+cfg.tableName+"ControllerImpl.java");
            fw.write(cfg.getProviderControllerImplText(cfg.providerPackageName + ".controller.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"Service\\"+cfg.tableName+"Service.java");
            fw.write(cfg.getConsumerFeignInterfaceText(cfg.providerPackageName + ".Service", cfg.tableName));
            fw.flush();
            fw.close();
        }

        {
            FileWriter fw = new FileWriter(cfg.providerPath+"Service\\impl\\"+cfg.tableName+"ServiceImpl.java");
            fw.write(cfg.getConsumerFeignImplText(cfg.providerPackageName + ".Service.impl", cfg.tableName));
            fw.flush();
            fw.close();
        }

    }
}
