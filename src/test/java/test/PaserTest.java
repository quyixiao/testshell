package test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import tsh.Node;
import tsh.Parser;
import tsh.Token;
import tsh.util.ClassUtils;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;

public class PaserTest {


    @Test
    public void testa() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/ab.bsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);

        while (!parser.Line()/*eof*/) {
            Node node = parser.popNode();
            System.out.println(JSON.toJSONString(node));
            break;
        }
    }


    @Test
    public void test() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/tab.tsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        int forabc = 0;
        while (!parser.Line()/*eof*/) {
            Node node = parser.popNode();
            System.out.println(JSON.toJSONString(node));
            break;

        }
    }


    @Test
    public void test2() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/abcd.bsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        for (int i = 0; i < 30; i++) {
            Token token = parser.token_source.getNextToken();
            System.out.println(token.image + " " + token.kind);
        }
    }


    @Test
    public void test3() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/git/java-python/script/while.tsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        for (int i = 0; i < 30; i++) {
            Token token = parser.token_source.getNextToken();
            System.out.println(i + "    "+token.image + "          :           " + (token.kind == "\n" ? "\\n" : token.kind));
        }
    }

    @Test
    public void test4() throws Exception{
        Method mt  =  ClassUtils.getMethod("println");
        Class clazz = mt.getDeclaringClass();
        System.out.println(clazz.getName());
        mt.invoke(clazz.newInstance(),new Object[]{new Object[]{"1",1,1.2}});
    }


}
