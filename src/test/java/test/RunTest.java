package test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import tsh.service.impl.ResouceHelp;
import tsh.t.Tuple1;
import tsh.util.TClassUtils;
import tsh.util.TFileUtils;
import tsh.util.Utils;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class RunTest {


    @Test
    public void test() throws Exception {
        ClassLoader classLoader = TClassUtils.getDefaultClassLoader();
        URL url = classLoader.getResource("base.tsh");
        String path = url.getPath();
        int a = path.lastIndexOf("/");
        path = path.substring(0, a);
        ResouceHelp resouceHelp = new ResouceHelp();
        Map<String, Object> map = new LinkedHashMap<>();
        for (File file : TFileUtils.getFiles(path)) {
            String content = TFileUtils.readToStr(file.getPath());
            Tuple1<Map<String, Object>> baseVarible = Utils.run(content, null, null, resouceHelp).getData();
            map.putAll(baseVarible.getFirst());
        }
        String content = TFileUtils.readToStr("/Users/quyixiao/git/java-python/script/yijie/login.tsh");
        Map<String, Object> imports = new LinkedHashMap<>();
        Object object = Utils.run(content, map, imports, resouceHelp);


    }
}




