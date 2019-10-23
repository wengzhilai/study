
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
        cfg.tableName="Script";
        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-quartz/src/main/java/com/quartz/";
        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-quartz/src/main/java/com/quartz/";
//        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-quartz\\src\\main\\java\\com\\quartz\\";
//        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-quartz\\src\\main\\java\\com\\quartz\\";
        cfg.consumerPackageName="com.quartz";
        cfg.providerPackageName="com.quartz";

        cfg.makeFileNum=Arrays.asList(2);
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
        cfg.tableName="Query";
        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
//        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-user-consumer/src/main/java/com/user/consumer/";
//        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPackageName="com.user.consumer";
        cfg.providerPackageName="com.user.provider";

        try {
            String funName="downFile";
            String reObjStr="Result";
            String inObj="DtoDo";
            String msg="下载文件";
            String tableName="Query";

            GenerateFile.MakeNewFunction(cfg,funName,reObjStr,inObj,msg,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void MakeEntity(){
        PathConfig cfg=new PathConfig();
        cfg.entityPath="D:\\IdeaProjects\\study_new\\study-dependencies\\src\\main\\java\\com\\wzl\\commons\\model\\entity\\";
        cfg.tableName="FA_SCRIPT_TASK_LOG";
        cfg.tableNameRmark="任务日志";
        cfg.clumStr="" +
                "ID\tID\tint\t\t\tTRUE\tFALSE\tTRUE\n" +
                "口径任务ID\tSCRIPT_TASK_ID\tint\t\t\tFALSE\tTRUE\tTRUE\n" +
                "记录时间\tLOG_TIME\tdatetime\t\t\tFALSE\tFALSE\tTRUE\n" +
                "日志级别\tLOG_TYPE\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
                "日志说明\tMESSAGE\ttext\t\t\tFALSE\tFALSE\tFALSE\n" +
                "SQL内容\tSQL_TEXT\ttext\t\t\tFALSE\tFALSE\tFALSE" +
                "";
        try {
            GenerateFile.MakeEntity(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
