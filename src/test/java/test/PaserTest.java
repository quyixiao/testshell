package test;

import org.junit.Test;
import tsh.Parser;
import tsh.Token;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class PaserTest {


    @Test
    public void test3() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/git/java-python/script/kelihua/kelihua11.tsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        for (int i = 0; i < 100; i++) {
            Token token = parser.token_source.getNextToken();
            System.out.println(i + "    " + token.image + "          :           " + (token.kind == "\n" ? "\\n" : token.kind));
        }
    }

    @Test
    public void test4() throws Exception {
       /* Method mt  =  ClassUtils.getMethod("println");
        Class clazz = mt.getDeclaringClass();
        System.out.println(clazz.getName());
        mt.invoke(clazz.newInstance(),new Object[]{new Object[]{"1",1,1.2}});
*/
        Map<String, Object> map = new HashMap<>();
        map.put("ksksk", 1);
        for (String a : map.keySet()) {
            System.out.println(a);
        }
    }

}
