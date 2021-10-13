package donga.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    static JsonParser jsonParser = new JsonParser();
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static XSSFSheet sheet = workbook.createSheet("data");
    static int i = 1;

    public static void main(String[] args) throws IOException {
        createExcelHead();

        showFilesInDir("C:\\Users\\82109\\Desktop\\data");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\summernote\\data.xlsx");
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
        setColumnWidth();
        Set<String> labelGroup = new HashSet<>();
        Set<String> startGroup = new HashSet<>();
        Set<String> endGroup = new HashSet<>();
        XSSFRow row = sheet.createRow(i++);
        for (JsonElement element : jsonArray) {
            setLabelGroup(labelGroup, element);
            setStartGroup(startGroup, element);
            setEndGroup(endGroup, element);
            row.createCell(0).setCellValue(file.getParent());
            row.createCell(1).setCellValue(jsonObject.get("id").getAsString());
            row.createCell(2).setCellValue(jsonObject.get("id").getAsString().substring(0, 5));
            getTargetValue(jsonObject, row);
            row.createCell(4).setCellValue("YES");
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
    }

    private static void isDuplicateFile(JsonObject jsonObject) {
        String id = jsonObject.get("id").getAsString().substring(0, 5);
        String string = sheet.getRow(i - 2).getCell(2).toString();
        if (id.equals(string)) {
            sheet.getRow(i-2).getCell(4).setCellValue("NO");
        }
    }

    private static void setEndGroup(Set<String> endGroup, JsonElement element) {
        String end = element.getAsJsonObject().get("end").getAsString();
        if (end.length() > 0) {
            endGroup.add(end);
        }
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
            labelGroup.add(str.trim());
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

    private static void createExcelHead() {
        XSSFRow initRow = sheet.createRow(0);
        initRow.createCell(0).setCellValue("filePath");
        initRow.createCell(1).setCellValue("id");
        initRow.createCell(2).setCellValue("ID(REPLACE)");
        initRow.createCell(3).setCellValue("Target1");
        initRow.createCell(4).setCellValue("Target2");
        initRow.createCell(5).setCellValue("age");
        initRow.createCell(6).setCellValue("gender");
        initRow.createCell(7).setCellValue("state");
        initRow.createCell(8).setCellValue("record_date");
        initRow.createCell(9).setCellValue("bitratio");
        initRow.createCell(10).setCellValue("seq");
        initRow.createCell(11).setCellValue("start");
        initRow.createCell(12).setCellValue("end");
        initRow.createCell(13).setCellValue("label");
    }

    private static void setColumnWidth() {
        sheet.setColumnWidth(0, 13500);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 2500);
        sheet.setColumnWidth(4, 2500);
        sheet.setColumnWidth(6, 2500);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 2500);
        sheet.setColumnWidth(11, 15000);
        sheet.setColumnWidth(12, 15000);
        sheet.setColumnWidth(13, 15000);
    }
}
