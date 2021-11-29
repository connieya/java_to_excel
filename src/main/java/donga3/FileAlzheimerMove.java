package donga3;

import java.io.File;

public class FileAlzheimerMove {
    public static void main(String[] args) {
        String filePath = "D:\\1_분절(after)\\0_그룹별_분류\\A";
        File original =  new File(filePath);
        File temp = new File("D:\\1_분절(after)\\0_그룹별_분류\\temp");
        File[] listFiles = original.listFiles();
        for (File file : listFiles) {
            String name = file.getName();
            String sub = name.substring(0, name.indexOf("_"));
            if (sub.contains("A") && !sub.contains("2")){
                File moveFile = new File(temp,file.getName());
                file.renameTo(moveFile);
            }

        }

    }

}
