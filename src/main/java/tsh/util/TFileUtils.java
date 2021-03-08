package tsh.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TFileUtils {


    public static String readToStr(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static List<File> getFiles (String path){
        File file = new File(path);
        File[] tempList = file.listFiles();
        List<File> result = new ArrayList<>();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                result.add(tempList[i]);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(readToStr("/Users/quyixiao/git/java-python/script/export.tsh"));
    }
}
