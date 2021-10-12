package donga.test;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFile {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\82109\\Desktop\\data\\A_210830_017_M_68";
        File[] files = new File(path).listFiles();
        JsonParser jsonParser = new JsonParser();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("정상인");

        int i = 0;
        for (File f : files) {
            Reader reader = new FileReader(f);
            JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);
            JsonElement id = jsonObject.get("id");
            JsonElement utterance = jsonObject.get("utterance");
            JsonArray jsonArray = utterance.getAsJsonArray();
            List<String > labelGroup = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                String label = element.getAsJsonObject().get("label").getAsString();
                labelGroup.add(label);
            }
            XSSFRow row = sheet.createRow(i++);
            row.createCell(0).setCellValue(id.getAsString());
            row.createCell(3).setCellValue(labelGroup.toString());
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\summernote\\data.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("액셀파일 생성 성공");
    }
}
