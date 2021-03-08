package test;

import org.junit.Test;
import tsh.util.TClassUtils;

import java.net.URL;
import java.util.Enumeration;

public class RunTest {


    @Test
    public void test() throws Exception {
        ClassLoader classLoader = TClassUtils.getDefaultClassLoader();
        URL url = classLoader.getResource("base.tsh");

        String path = url.getPath();
        System.out.println(path);
    }
}

