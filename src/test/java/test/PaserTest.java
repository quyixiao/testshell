package test;

import bsh.*;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PaserTest {



    public static void main(String[] args) throws IOException, ParseException {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/for.bsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        while (!parser.Line()/*eof*/) {
            Node node = parser.popNode();
            System.out.println(JSON.toJSONString(node));
        }

    }





    @Test
    public void testa() throws Exception{
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/ab.bsh");
        Parser parser = new Parser(in);
        parser.setRetainComments(true);
        while (!parser.Line()/*eof*/) {
            Node node = parser.popNode();
            System.out.println(JSON.toJSONString(node));
        }
    }




    @Test
    public void test() throws Exception{
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/tab.tsh");
        TParser parser = new TParser(in);
        parser.setRetainComments(true);
        int forabc = 0;
        while (!parser.Line()/*eof*/) {
            Node node = parser.popNode();
            System.out.println(JSON.toJSONString(node));
            break;

        }
    }




    @Test
    public void test2() throws Exception{
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/abcd.bsh");
        TParser parser = new TParser(in);
        parser.setRetainComments(true);
        for(int i = 0 ; i < 30 ;i ++){
            Token token = parser.token_source.getNextToken();
            System.out.println(token.image + " " + token.tKind);
        }
    }





    @Test
    public void test3() throws Exception{
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/main/resources/abcd.bsh");
        TParser parser = new TParser(in);
        parser.setRetainComments(true);
        for(int i = 0 ; i < 30 ;i ++){
            Token token = parser.token_source.getNextToken();
            System.out.println(token.image + "          :           " + token.tKind);
        }
    }


}
