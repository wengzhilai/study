
import com.wzl.commons.model.entity.FaLoginEntity;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.generate.GenerateFile;
import com.wzl.commons.utlity.generate.PathConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =GenerateTests.class )
public class GenerateTests {

    /**
     * 生成程序文件
     */
    @Test
    public void MakeClassFile() {
        PathConfig cfg=new PathConfig();
        cfg.tableName="Equipment";
//        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-quartz/src/main/java/com/quartz/";
//        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-quartz/src/main/java/com/quartz/";
        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-equipment-consumer/src/main/java/com/equipment/consumer/";
        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-equipment-provider/src/main/java/com/equipment/provider/";

//        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-quartz\\src\\main\\java\\com\\quartz\\";
//        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-quartz\\src\\main\\java\\com\\quartz\\";
        cfg.consumerPackageName="com.equipment.consumer";
        cfg.providerPackageName="com.equipment.provider";

        cfg.makeFileNum=Arrays.asList(7,8);
        try {
            GenerateFile.Start(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加新方法
     */
    @Test
    public void AddFunciton() {
        PathConfig cfg=new PathConfig();
        cfg.tableName="equipment";
//        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-equipment-consumer/src/main/java/com/equipment/consumer/";
//        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-equipment-provider/src/main/java/com/equipment/provider/";
//        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
//        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
//        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-user-consumer/src/main/java/com/user/consumer/";
//        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-equipment-consumer\\src\\main\\java\\com\\equipment\\consumer\\";
        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-equipment-provider\\src\\main\\java\\com\\equipment\\provider\\";
        cfg.consumerPackageName="com.equipment.consumer";
        cfg.providerPackageName="com.equipment.provider";

        try {
            String funName="deleteEquiment";
            String reObjStr="ResultObj<Integer>";
            String inObj="EquipmentDto";
            String msg="删除设备";
            String tableName="Equipment";

            GenerateFile.MakeNewFunction(cfg,funName,reObjStr,inObj,msg,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void MakeEntity(){
        PathConfig cfg=new PathConfig();
//        cfg.entityPath="D:\\IdeaProjects\\study_new\\study-dependencies\\src\\main\\java\\com\\wzl\\commons\\model\\entity\\";
        cfg.entityPath="/Users/wengzhilai/Desktop/java/study/study-dependencies/src/main/java/com/wzl/commons/model/entity/";
        cfg.tableName="fa_table_type";
        cfg.tableNameRmark="自定义表";
//        cfg.clumStr="" +
//                "ID\tID\tint\t\t\tTRUE\tFALSE\tTRUE\n" +
//                "口径任务ID\tSCRIPT_TASK_ID\tint\t\t\tFALSE\tTRUE\tTRUE\n" +
//                "记录时间\tLOG_TIME\tdatetime\t\t\tFALSE\tFALSE\tTRUE\n" +
//                "日志级别\tLOG_TYPE\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
//                "日志说明\tMESSAGE\ttext\t\t\tFALSE\tFALSE\tFALSE\n" +
//                "SQL内容\tSQL_TEXT\ttext\t\t\tFALSE\tFALSE\tFALSE" +
//                "";
        try {
            GenerateFile.MakeEntity(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
