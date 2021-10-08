package donga.test;


import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class FileRecursive {
    public static void main(String[] args) {
        showFilesInDir("C:\\Users\\82109\\Desktop\\json\\A");
    }

    public static void showFilesInDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            checkDirectory(file);
        }
    }

    private static void checkDirectory(File file) {
        if (file.isDirectory()) {
            showFilesInDir(file.getPath());
        }
        isJsonFile(file);
    }

    private static void isJsonFile(File file) {
        String ext = FilenameUtils.getExtension(file.getName());
        if (ext.equals("json")) {
            System.out.println(file);
        }
    }
}
