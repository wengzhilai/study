
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
        cfg.tableName="Role";
        cfg.consumerPath="/Users/wengzhilai/Desktop/java/study/study-user-consumer/src/main/java/com/user/consumer/";
        cfg.providerPath="/Users/wengzhilai/Desktop/java/study/study-user-provider/src/main/java/com/user/provider/";
        cfg.consumerPackageName="com.user.consumer";
        cfg.providerPackageName="com.user.provider";

        try {
            String funName="test";
            String reObjStr="Result";
            String inObj="DtoDo";
            String msg="测试";
            String tableName="Role";

            GenerateFile.MakeNewFunction(cfg,funName,reObjStr,inObj,msg,tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void MakeEntity(){
        PathConfig cfg=new PathConfig();
        cfg.tableName="fa_role";
        cfg.clumStr="" +
                "";
        try {
            GenerateFile.MakeEntity(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
