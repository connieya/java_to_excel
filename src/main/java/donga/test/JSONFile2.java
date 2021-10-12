package donga.test;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFile2 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\82109\\Desktop\\data\\A_210830_017_M_68";
        File[] files = new File(path).listFiles();
        JsonParser jsonParser = new JsonParser();
        Map<JsonElement , Integer> map = new HashMap<>();
        for (File f : files) {
            Reader reader = new FileReader(f);
            JsonElement parse = jsonParser.parse(reader);
            if (parse.isJsonObject()) {
                JsonObject jsonObject = parse.getAsJsonObject();
                JsonArray jsonArray = jsonObject.get("utterance").getAsJsonArray();
                List<String> labelGroup = new ArrayList<>();
                for (JsonElement element : jsonArray) {
                    System.out.println(f+" = "+jsonObject.get("id")+" :" + element.getAsJsonObject().get("label"));
                }
            }
        }
    }
}
