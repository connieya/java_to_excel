package move;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Copy {
    public static void main(String[] args) throws IOException {
        String originalFilePath = "C:\\Users\\82109\\Desktop\\voice";
        String copyFilePath = "C:\\Users\\82109\\Desktop\\image";

        File originalFile = new File(originalFilePath);
        File copyFile = new File(copyFilePath);
        File[] listFiles = originalFile.listFiles();


        FileUtils.copyFile(originalFile,copyFile);
    }
}
