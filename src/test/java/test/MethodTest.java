package test;

import org.junit.Test;
import tsh.service.impl.ResouceHelp;
import tsh.t.TTuple1;
import tsh.t.TTuple3;
import tsh.util.TClassUtils;
import tsh.util.TFileUtils;
import tsh.util.Utils;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MethodTest {



    @Test
    public void test1() throws Exception {
        ClassLoader classLoader = TClassUtils.getDefaultClassLoader();
        URL url = classLoader.getResource("base.tsh");
        String path = url.getPath();
        int a = path.lastIndexOf("/");
        path = path.substring(0, a);
        ResouceHelp resouceHelp = new ResouceHelp();
        Map<String, Object> init = new LinkedHashMap<>();
        for (File file : TFileUtils.getFiles(path)) {
            String content = TFileUtils.readToStr(file.getPath());
            TTuple1<Map<String, Object>> baseVarible = Utils.run(content, null, null, null, resouceHelp).getData();
            init.putAll(baseVarible.getFirst());
        }

        Map<String, Object> globals = new LinkedHashMap<>();
        Map<String, Object> imports = new LinkedHashMap<>();


        List<String> files = Arrays.asList(new String[]{
                "/Users/quyixiao/git/java-python/script/kelihua/kelihua10.tsh"});

        TTuple3<Map<String, Object>, Map<String, Object>, Map<String, Object>> data = null;
        for (String f : files) {
            String content = TFileUtils.readToStr(f);
            data = Utils.run(content, init, globals, imports, resouceHelp).getData();
            globals = data.getSecond();
            imports = data.getThird();
        }

    }
}
