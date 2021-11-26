package donga2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExcelDtoTest {

    JsonParser jsonParser = new JsonParser();

    @Test
    void jsonNullTest() throws FileNotFoundException {
        String jsonPath = "C:\\Users\\82109\\Desktop\\data\\A_210827_011_F_51\\05-01.json";
        Reader reader = new FileReader(jsonPath);
        JsonElement parse = jsonParser.parse(reader);
        assertThat(parse.isJsonObject()).isFalse();
    }

    @Test
    void jsonNullTest2() throws FileNotFoundException {
        String jsonPath = "C:\\Users\\82109\\Desktop\\data\\A_210827_011_F_51\\05-21.json";
        Reader reader = new FileReader(jsonPath);
        JsonElement parse = jsonParser.parse(reader);
        assertThat(parse.isJsonObject()).isTrue();
    }

    @Test
    void countUtterance() throws FileNotFoundException {
        String folder = "C:\\Users\\82109\\Desktop\\data\\ZN_211007_134_M_81";
        File jsonFiles = new File(folder);
        File[] files = jsonFiles.listFiles();
        int[] utteranceInfo = new int[120];
        int index = 0;
        int count = 0;
        for (File file : files) {
            String id = file.getName().substring(0, 5);
            if (id.compareTo("07-00") > 0) {
                Reader reader = new FileReader(file);
                JsonElement parse = jsonParser.parse(reader);
                if (!parse.isJsonObject()) {
                    continue;
                }
                JsonArray utterance = parse.getAsJsonObject().get("utterance").getAsJsonArray();
                for (JsonElement element : utterance) {
                    JsonElement label = element.getAsJsonObject().get("label");
                    System.out.println(file.getName() + " : " + element.getAsJsonObject().get("label"));
                }
            }
        }
        System.out.println(count);
    }

    @Test
    void jsonParser() throws FileNotFoundException {
        String file = "C:\\Users\\82109\\Desktop\\data\\ZN_211007_134_M_81\\07-06_1(new_24).json";
        Reader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        if (!parse.isJsonObject()) {
            return;
        }
        int utterance_o2 = 0;
        int utterance_b3 = 0;
        int utterance_g4 = 0;
        int utterance_d1 = 0;
        JsonArray utterance = parse.getAsJsonObject().get("utterance").getAsJsonArray();
        for (JsonElement element : utterance) {
            String label = element.getAsJsonObject().get("label").getAsString();
            String[] labelArray = label.split(",");
            for (String str : labelArray) {
                String trim = str.trim();
                if (trim.equals("오발화")) {
                    utterance_o2++;
                    continue;
                }
                if (trim.equals("반복발화")) {
                    utterance_b3++;
                    continue;
                }
                if (trim.equals("간투어")) {
                    utterance_g4++;
                    continue;
                }
                if (trim.equals("단절발화")) {
                    utterance_d1++;
                    continue;
                }
            }
        }
        int finalUtterance_o = utterance_o2;
        int finalUtterance_b = utterance_b3;
        int finalUtterance_d = utterance_d1;
        int finalUtterance_g = utterance_g4;
        assertAll(
                () -> assertThat(finalUtterance_o).isEqualTo(7),
                () -> assertThat(finalUtterance_b).isEqualTo(1),
                () -> assertThat(finalUtterance_g).isEqualTo(1),
                () -> assertThat(finalUtterance_d).isEqualTo(0)
        );
    }
    @Test
    void jsonParser2() throws FileNotFoundException {
        String file = "C:\\Users\\82109\\Desktop\\data\\ZN_211007_134_M_81\\07-10_2(new_24).json";
        Reader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        if (!parse.isJsonObject()) {
            return;
        }
        int utterance_o2 = 0;
        int utterance_b3 = 0;
        int utterance_g4 = 0;
        int utterance_d1 = 0;
        JsonArray utterance = parse.getAsJsonObject().get("utterance").getAsJsonArray();
        for (JsonElement element : utterance) {
            String label = element.getAsJsonObject().get("label").getAsString();
            String[] labelArray = label.split(",");
            for (String str : labelArray) {
                String trim = str.trim();
                if (trim.equals("오발화")) {
                    utterance_o2 += 1;
                    continue;
                }
                if (trim.equals("반복발화")) {
                    utterance_b3++;
                    continue;
                }
                if (trim.equals("간투어")) {
                    utterance_g4++;
                    continue;
                }
                if (trim.equals("단절발화")) {
                    utterance_d1++;
                    continue;
                }
            }
        }
        int finalUtterance_o = utterance_o2;
        int finalUtterance_b = utterance_b3;
        int finalUtterance_d = utterance_d1;
        int finalUtterance_g = utterance_g4;
        assertAll(
                () -> assertThat(finalUtterance_o).isEqualTo(3),
                () -> assertThat(finalUtterance_b).isEqualTo(0),
                () -> assertThat(finalUtterance_g).isEqualTo(0),
                () -> assertThat(finalUtterance_d).isEqualTo(0)
        );
    }

    @Test
    void jsonParser3() throws FileNotFoundException {
        String file = "C:\\Users\\82109\\Desktop\\data\\ZN_211007_134_M_81\\07-08_1(new_24).json";
        Reader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        if (!parse.isJsonObject()) {
            return;
        }
        int utterance_o2 = 0;
        int utterance_b3 = 0;
        int utterance_g4 = 0;
        int utterance_d1 = 0;
        JsonArray utterance = parse.getAsJsonObject().get("utterance").getAsJsonArray();
        for (JsonElement element : utterance) {
            String label = element.getAsJsonObject().get("label").getAsString();
            String[] labelArray = label.split(",");
            for (String str : labelArray) {
                String trim = str.trim();
                if (trim.equals("오발화")) {
                    utterance_o2 += 1;
                    continue;
                }
                if (trim.equals("반복발화")) {
                    utterance_b3++;
                    continue;
                }
                if (trim.equals("간투어")) {
                    utterance_g4++;
                    continue;
                }
                if (trim.equals("단절발화")) {
                    utterance_d1++;
                    continue;
                }
            }
        }
        int finalUtterance_o = utterance_o2;
        int finalUtterance_b = utterance_b3;
        int finalUtterance_d = utterance_d1;
        int finalUtterance_g = utterance_g4;
        assertAll(
                () -> assertThat(finalUtterance_o).isEqualTo(0),
                () -> assertThat(finalUtterance_b).isEqualTo(0),
                () -> assertThat(finalUtterance_g).isEqualTo(0),
                () -> assertThat(finalUtterance_d).isEqualTo(2)
        );
    }
}


