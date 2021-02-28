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




    @Test
    public void a (){
        double a = 1  / 0.5;
        System.out.println(a);
    }


}
