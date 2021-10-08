package donga.test;


import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class FileRecursive {
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 5;
    static JsonParser jsonParser = new JsonParser();

    public static void main(String[] args) throws FileNotFoundException {
        showFilesInDir("C:\\Users\\82109\\Desktop\\data");
    }

    public static void showFilesInDir(String dirPath) throws FileNotFoundException {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            checkDirectory(file);
        }
    }

    private static void checkDirectory(File file) throws FileNotFoundException {
        if (file.isDirectory()) {
            showFilesInDir(file.getPath());
        }
        isJsonFile(file);
    }

    private static void isJsonFile(File file) throws FileNotFoundException {
        String ext = FilenameUtils.getExtension(file.getName());
        if (ext.equals("json")) {
            getJsonObject(file);
        }
    }

    private static void getJsonObject(File file) throws FileNotFoundException {
        Reader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        if (parse.isJsonObject()) {
            JsonObject jsonObject = parse.getAsJsonObject();
            JsonElement id = jsonObject.get("id");
            JsonElement utterance = jsonObject.get("utterance");
            System.out.println(file+" :" +utterance);
        }

    }
}
