package test;

import tsh.Interpreter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        int i39 = 10;
        i39 >>>= 3;
        System.out.println(i39);
    }

}
