package donga3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMove {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\82109\\Desktop\\text\\text.txt"));
        List<String> fileList = new ArrayList<>();
        String str;
        while ((str = reader.readLine()) != null) {
            fileList.add(str);
        }
        File target = new File("D:\\1_분절(after)\\0_그룹별_분류\\N");
        File dir = new File("D:\\1_분절(after)\\0_그룹별_분류\\temp");

        File[] listFiles = target.listFiles();
        for (File file : listFiles) {
            if (fileList.contains(file.getName())) {
                File moveFile = new File(dir, file.getName());
                file.renameTo(moveFile);
            }
        }
        System.out.println("작업이 완료되었습니다.");

        reader.close();
    }
}
