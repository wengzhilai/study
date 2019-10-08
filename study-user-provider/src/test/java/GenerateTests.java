
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
        cfg.tableName="Role";
        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
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
        cfg.tableName="fa_bulletin";
        cfg.tableNameRmark="公告";
        cfg.clumStr="" +
                "ID\tID\tint\t\t\tTRUE\tFALSE\tTRUE\n" +
                "标题\tTITLE\tvarchar(255)\t255\t\tFALSE\tFALSE\tTRUE\n" +
                "图片\tPIC\tvarchar(255)\t255\t\tFALSE\tFALSE\tFALSE\n" +
                "公告类型\tTYPE_CODE\tvarchar(50)\t50\t\tFALSE\tFALSE\tFALSE\n" +
                "内容\tCONTENT\ttext\t\t\tFALSE\tFALSE\tFALSE\n" +
                "发布人ID\tUSER_ID\tint\t\t\tFALSE\tFALSE\tFALSE\n" +
                "发布人\tPUBLISHER\tvarchar(255)\t255\t\tFALSE\tFALSE\tTRUE\n" +
                "生效时间\tISSUE_DATE\tdatetime\t\t\tFALSE\tFALSE\tTRUE\n" +
                "显示\tIS_SHOW\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
                "重要\tIS_IMPORT\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
                "置顶\tIS_URGENT\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
                "自动打开\tAUTO_PEN\tnumeric(1)\t1\t\tFALSE\tFALSE\tTRUE\n" +
                "创建时间\tCREATE_TIME\tdatetime\t\t\tFALSE\tFALSE\tTRUE\n" +
                "修改时间\tUPDATE_TIME\tdatetime\t\t\tFALSE\tFALSE\tTRUE\n" +
                "REGION\tREGION\tvarchar(10)\t10\t\tFALSE\tFALSE\tTRUE";
        try {
            GenerateFile.MakeEntity(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
