package test;

import tsh.Interpreter;
import org.junit.Test;

public class TestXX {



    @Test
    public void testAMulB() throws Exception {
        Interpreter i = new Interpreter();  // Construct an interpreter
        try {
            Object object = i.source("/Users/quyixiao/project/testshell/src/main/resources/ab.bsh");
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
