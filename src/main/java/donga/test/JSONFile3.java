package donga.test;

import donga.domain.JsonState;

import java.io.File;

public class JSONFile3 {
    public static void main(String[] args) {
        String path = "C:\\Users\\82109\\Desktop\\json";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            int startIndex = f.toString().lastIndexOf("\\");
            String substring = f.toString().substring(startIndex + 1, startIndex + 3);
            System.out.println(f + " , " +substring.contains(JsonState.A.name()));
        }
    }

}
