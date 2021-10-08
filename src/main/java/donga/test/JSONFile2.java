package donga.test;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class JSONFile2 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\82109\\Desktop\\json\\N\\N_210825_004_F_71";
        File[] files = new File(path).listFiles();
        JsonParser jsonParser = new JsonParser();
        Map<JsonElement , Integer> map = new HashMap<>();
        for (File f : files) {
            Reader reader = new FileReader(f);
            JsonElement parse = jsonParser.parse(reader);
            if (parse.isJsonObject()) {
                JsonObject jsonObject = parse.getAsJsonObject();
                JsonElement element = jsonObject.get("metadata");
                JsonElement gender = element.getAsJsonObject().get("gender");
                System.out.println(jsonObject);
            }
        }
    }
}
