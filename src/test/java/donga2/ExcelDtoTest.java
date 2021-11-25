package donga2;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ExcelDtoTest {

    JsonParser jsonParser = new JsonParser();

    @Test
    void jsonNullTest() throws FileNotFoundException {
        String jsonPath = "C:\\Users\\82109\\Desktop\\data\\A_210827_011_F_51\\05-01.json";
        Reader reader = new FileReader(jsonPath);
        JsonElement parse = jsonParser.parse(reader);
        Assertions.assertThat(parse.isJsonObject()).isFalse();

    }

    @Test
    void jsonNullTest2() throws FileNotFoundException {
        String jsonPath = "C:\\Users\\82109\\Desktop\\data\\A_210827_011_F_51\\05-21.json";
        Reader reader = new FileReader(jsonPath);
        JsonElement parse = jsonParser.parse(reader);
        Assertions.assertThat(parse.isJsonObject()).isTrue();

    }
}
