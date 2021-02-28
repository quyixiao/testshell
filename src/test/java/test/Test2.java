package test;

import tsh.util.JavaCharStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Test2 {

    public static void main(String[] args) throws Exception {

        File file = new File("/Users/quyixiao/project/testshell/src/main/resources/ab.bsh");
        Reader sourceIn = new BufferedReader(new FileReader(file));
        JavaCharStream jj_input_stream = new JavaCharStream(sourceIn, 1, 1);

        char a = jj_input_stream.BeginToken();
        System.out.println(a);
        System.out.println("bufpos:" + jj_input_stream.bufpos + ",bufsize:" + jj_input_stream.bufsize + ",available=" + jj_input_stream.available + ",tokenBegin=" + jj_input_stream.tokenBegin);
        // System.out.println(Arrays.toString(jj_input_stream.buffer));
        System.out.println("beginColumn:" + jj_input_stream.getBeginColumn() + ",endColumn:" + jj_input_stream.getEndColumn() + ",beginLine:" + jj_input_stream.getBeginLine() + ",endLine:" + jj_input_stream.getEndLine());
        System.out.println("====================");


        char b = jj_input_stream.readChar();
        System.out.println(b);
        System.out.println("bufpos:" + jj_input_stream.bufpos + ",bufsize:" + jj_input_stream.bufsize + ",available=" + jj_input_stream.available + ",tokenBegin=" + jj_input_stream.tokenBegin);
        //System.out.println(Arrays.toString(jj_input_stream.buffer));
        System.out.println("beginColumn:" + jj_input_stream.getBeginColumn() + ",endColumn:" + jj_input_stream.getEndColumn() + ",beginLine:" + jj_input_stream.getBeginLine() + ",endLine:" + jj_input_stream.getEndLine());
        System.out.println("====================");


        jj_input_stream.backup(2);
        char c = jj_input_stream.readChar();
        System.out.println(c);
        System.out.println("bufpos:" + jj_input_stream.bufpos + ",bufsize:" + jj_input_stream.bufsize + ",available=" + jj_input_stream.available + ",tokenBegin=" + jj_input_stream.tokenBegin);
        //System.out.println(Arrays.toString(jj_input_stream.buffer));
        System.out.println("beginColumn:" + jj_input_stream.getBeginColumn() + ",endColumn:" + jj_input_stream.getEndColumn() + ",beginLine:" + jj_input_stream.getBeginLine() + ",endLine:" + jj_input_stream.getEndLine());
        System.out.println("====================");
    }
}
