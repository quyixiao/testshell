package test;

import bsh.Node;
import bsh.ParseException;
import bsh.Parser;
import com.alibaba.fastjson.JSON;

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
}
