
import com.wzl.commons.model.entity.FaLoginEntity;
import com.wzl.commons.retention.EntityHelper;
import com.wzl.commons.utlity.generate.GenerateFile;
import com.wzl.commons.utlity.generate.PathConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =GenerateTests.class )
public class GenerateTests {

    /**
     * 生成程序文件
     */
    @Test
    public void MakeClassFile() {
        PathConfig cfg=new PathConfig();
        cfg.tableName="User";
//        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
//        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-user-consumer/src/main/java/com/user/consumer/";
        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPackageName="com.user.consumer";
        cfg.providerPackageName="com.user.provider";
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
//        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
//        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPath="D:\\IdeaProjects\\study_new\\study-user-consumer/src/main/java/com/user/consumer/";
        cfg.providerPath="D:\\IdeaProjects\\study_new\\study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPackageName="com.user.consumer";
        cfg.providerPackageName="com.user.provider";

        try {
            String funName="delete";
            String reObjStr="Result";
            String inObj="DtoDo";
            String msg="删除Query";
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
        cfg.tableName="FA_USER";
        cfg.tableNameRmark="用户";
        cfg.clumStr="" +
                "ID\tID\tint\t\t\tTRUE\tFALSE\tTRUE\n" +
                "姓名\tNAME\tvarchar(80)\t80\t\tFALSE\tFALSE\tFALSE\n" +
                "登录名\tLOGIN_NAME\tvarchar(20)\t20\t\tFALSE\tFALSE\tFALSE\n" +
                "头像图片\tICON_FILES_ID\tint\t\t\tFALSE\tFALSE\tFALSE\n" +
                "归属地\tDISTRICT_ID\tint\t\t\tFALSE\tTRUE\tTRUE\n" +
                "锁定\tIS_LOCKED\tnumeric(1)\t1\t\tFALSE\tFALSE\tFALSE\n" +
                "创建时间\tCREATE_TIME\tdatetime\t\t\tFALSE\tFALSE\tFALSE\n" +
                "登录次数\tLOGIN_COUNT\tint\t\t\tFALSE\tFALSE\tFALSE\n" +
                "最后登录时间\tLAST_LOGIN_TIME\tdatetime\t\t\tFALSE\tFALSE\tFALSE\n" +
                "最后离开时间\tLAST_LOGOUT_TIME\tdatetime\t\t\tFALSE\tFALSE\tFALSE\n" +
                "最后活动时间\tLAST_ACTIVE_TIME\tdatetime\t\t\tFALSE\tFALSE\tFALSE\n" +
                "备注\tREMARK\tvarchar(2000)\t2,000\t\tFALSE\tFALSE\tFALSE" +
                "";
        try {
            GenerateFile.MakeEntity(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
