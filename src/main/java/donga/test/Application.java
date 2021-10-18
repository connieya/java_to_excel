package donga.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import donga.domain.ExcelFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Application {
    static int i = 1;
    static JsonParser jsonParser = new JsonParser();
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static XSSFSheet sheet = workbook.createSheet("data");


    public static void main(String[] args) throws IOException {
        ExcelFactory.createExcelHead(sheet);
        ExcelFactory.setColumnWidth(sheet);
        showFilesInDir("C:\\Users\\82109\\Desktop\\json");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\summernote\\test.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("액셀파일 생성 성공");
    }

    public static void showFilesInDir(String dirPath) throws IOException {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            checkDirectory(file);
        }
    }

    private static void checkDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            showFilesInDir(file.getPath());
        }
        isJsonFile(file);
    }

    private static void isJsonFile(File file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getName());
        if (ext.equals("json")) {
            getJsonObject(file);
        }
    }

    private static void getJsonObject(File file) throws IOException {
        Reader reader = new FileReader(file);
        JsonElement parse = jsonParser.parse(reader);
        if (parse.isJsonObject()) {
            createCell(file, parse);
        }
    }

    private static void createCell(File file, JsonElement parse) {
        JsonObject jsonObject = parse.getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("utterance").getAsJsonArray();
        JsonElement metadata = jsonObject.get("metadata");
        Set<String> labelGroup = new HashSet<>();
        Set<String> startGroup = new HashSet<>();
        Set<String> endGroup = new HashSet<>();
        Set<String> noteGroup = new HashSet<>();
        XSSFRow row = sheet.createRow(i++);
        if (jsonArray.toString().equals("[]"))  {
            row.createCell(0).setCellValue(file.getParent());
            row.createCell(1).setCellValue(jsonObject.get("id").getAsString());
            row.createCell(2).setCellValue(jsonObject.get("id").getAsString().substring(0, 5));
            getTargetValue(jsonObject, row);
            row.createCell(4).setCellValue("YES");
            row.createCell(5).setCellValue(metadata.getAsJsonObject().get("age").getAsString());
            row.createCell(6).setCellValue(metadata.getAsJsonObject().get("gender").getAsString());
            row.createCell(7).setCellValue(metadata.getAsJsonObject().get("state").getAsString());
            row.createCell(8).setCellValue(metadata.getAsJsonObject().get("record_date").getAsString());
            row.createCell(9).setCellValue(metadata.getAsJsonObject().get("bitratio").getAsString());
            row.createCell(10).setCellValue("empty");
            row.createCell(11).setCellValue("empty");
            row.createCell(12).setCellValue("empty");
            row.createCell(13).setCellValue("empty");
            row.createCell(14).setCellValue("empty");
            return;
        }

        for (JsonElement element : jsonArray) {
            setLabelGroup(labelGroup, element);
            setStartGroup(startGroup, element);
            setEndGroup(endGroup, element);
            setNoteGroup(noteGroup, element);
            row.createCell(0).setCellValue(file.getParent());
            row.createCell(1).setCellValue(jsonObject.get("id").getAsString());
            row.createCell(2).setCellValue(jsonObject.get("id").getAsString().substring(0, 5));
            getTargetValue(jsonObject, row);
            row.createCell(4).setCellValue("YES");
            System.out.println(file.getParent() +" :  " + element +"  : ");
            isDuplicateFile(jsonObject);
            row.createCell(5).setCellValue(metadata.getAsJsonObject().get("age").getAsString());
            row.createCell(6).setCellValue(metadata.getAsJsonObject().get("gender").getAsString());
            row.createCell(7).setCellValue(metadata.getAsJsonObject().get("state").getAsString());
            row.createCell(8).setCellValue(metadata.getAsJsonObject().get("record_date").getAsString());
            row.createCell(9).setCellValue(metadata.getAsJsonObject().get("bitratio").getAsString());
            row.createCell(10).setCellValue(element.getAsJsonObject().get("seq").getAsString());
        }
        row.createCell(11).setCellValue(startGroup.toString());
        row.createCell(12).setCellValue(endGroup.toString());
        row.createCell(13).setCellValue(labelGroup.toString());
        row.createCell(14).setCellValue(noteGroup.toString());
    }

    private static void setNoteGroup(Set<String> noteGroup, JsonElement element) {
        String note = element.getAsJsonObject().get("note").getAsString();
        noteGroup.add(note);
    }


    private static void isDuplicateFile(JsonObject jsonObject) {

        String id = jsonObject.get("id").getAsString().substring(0, 5);
        String string = sheet.getRow(i - 2).getCell(2).toString();
        if (id.equals(string)) {
            sheet.getRow(i - 2).getCell(4).setCellValue("NO");
        }
    }

    private static void setEndGroup(Set<String> endGroup, JsonElement element) {
        String end = element.getAsJsonObject().get("end").getAsString();
        endGroup.add(end);

    }

    private static void setStartGroup(Set<String> startGroup, JsonElement element) {
        String start = element.getAsJsonObject().get("start").getAsString();
        if (start.length() > 0) {
            startGroup.add(start);
        }
    }

    private static void setLabelGroup(Set<String> labelGroup, JsonElement element) {
        String label = element.getAsJsonObject().get("label").getAsString();
        String[] split = label.split(",");
        for (String str : split) {
            if (str.length() > 0) {
                labelGroup.add(str.trim());
            }
        }
    }

    private static void getTargetValue(JsonObject jsonObject, XSSFRow row) {
        String id = jsonObject.get("id").getAsString().substring(0, 5);
        if (id.compareTo("05-01") < 0) {
            row.createCell(3).setCellValue("NO");
        } else {
            row.createCell(3).setCellValue("YES");
        }
    }
}
