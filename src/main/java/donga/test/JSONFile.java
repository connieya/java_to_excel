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

public class JSONFile {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\82109\\Desktop\\json\\test";
        File[] files = new File(path).listFiles();
        JsonParser jsonParser = new JsonParser();
        for (File f : files) {
            Reader reader = new FileReader(f);
            JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);
            JsonElement id = jsonObject.get("id");
            JsonElement utterance = jsonObject.get("utterance");
            JsonArray jsonArray = utterance.getAsJsonArray();
            Map<JsonElement , Integer> map = new HashMap<>();
            for (JsonElement element : jsonArray) {
                JsonElement label = element.getAsJsonObject().get("label");
                map.put(label, map.getOrDefault(map,0)+1);
            }
            System.out.println(id +" :" +map);

        }
    }
}
