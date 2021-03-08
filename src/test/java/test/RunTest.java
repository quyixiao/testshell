package test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import tsh.util.FileUtils;
import tsh.util.Utils;

public class RunTest {


    @Test
    public void test() throws Exception {
        Object retVal = null;
        String file = "/Users/quyixiao/git/java-python/script/export.tsh";
        Object object = Utils.run(FileUtils.readToStr(file));
        System.out.println(JSON.toJSONString(object));

    }
}

