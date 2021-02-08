package test;

import bsh.Interpreter;
import org.junit.Test;

public class TestXX {




    @Test
    public void testAMulB() throws Exception {
        Interpreter i = new Interpreter();  // Construct an interpreter
        try {
            Object object = i.source("/Users/quyixiao/project/TShell/src/main/resources/ab.bsh");
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testb() throws Exception {
        Interpreter i = new Interpreter();  // Construct an interpreter
        try {
            Object object = i.source("/Users/quyixiao/project/TShell/src/main/resources/for.bsh");
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
