package donga.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Application {
    static JsonParser jsonParser = new JsonParser();
    static int i = 1;
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static XSSFSheet sheet = workbook.createSheet("data");
    public static void main(String[] args) throws IOException {
        showFilesInDir("C:\\Users\\82109\\Desktop\\json");
        XSSFRow initRow = sheet.createRow(0);
        initRow.createCell(0).setCellValue("filePath");
        initRow.createCell(1).setCellValue("id");
        initRow.createCell(2).setCellValue("age");
        initRow.createCell(3).setCellValue("gender");
        initRow.createCell(4).setCellValue("state");
        initRow.createCell(5).setCellValue("record_date");
        initRow.createCell(6).setCellValue("bitratio");
        initRow.createCell(7).setCellValue("seq");
        initRow.createCell(8).setCellValue("start");
        initRow.createCell(9).setCellValue("end");
        initRow.createCell(10).setCellValue("label");
        initRow.createCell(11).setCellValue("note");
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
            JsonObject jsonObject = parse.getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("utterance").getAsJsonArray();
            JsonElement metadata = jsonObject.get("metadata");
            sheet.setColumnWidth(0,12500);
            sheet.setColumnWidth(1,3500);
            sheet.setColumnWidth(4,3000);
            sheet.setColumnWidth(5,3000);
            sheet.setColumnWidth(6,3000);
            sheet.setColumnWidth(8,3000);
            sheet.setColumnWidth(9,3000);
            sheet.setColumnWidth(10,5000);
            for (JsonElement element : jsonArray) {
                XSSFRow row = sheet.createRow(i++);
                row.createCell(0).setCellValue(file.getParent());
                row.createCell(1).setCellValue(jsonObject.get("id").getAsString());
                row.createCell(2).setCellValue(metadata.getAsJsonObject().get("age").getAsString());
                row.createCell(3).setCellValue(metadata.getAsJsonObject().get("gender").getAsString());
                row.createCell(4).setCellValue(metadata.getAsJsonObject().get("state").getAsString());
                row.createCell(5).setCellValue(metadata.getAsJsonObject().get("record_date").getAsString());
                row.createCell(6).setCellValue(metadata.getAsJsonObject().get("bitratio").getAsString());
                row.createCell(7).setCellValue(element.getAsJsonObject().get("seq").getAsString());
                row.createCell(8).setCellValue(element.getAsJsonObject().get("start").getAsString());
                row.createCell(9).setCellValue(element.getAsJsonObject().get("end").getAsString());
                row.createCell(10).setCellValue(element.getAsJsonObject().get("label").getAsString());
                row.createCell(11).setCellValue(element.getAsJsonObject().get("note").getAsString());
                System.out.println(file.getParent()+","+jsonObject.get("id")+","+element.getAsJsonObject().get("label")+","+metadata.getAsJsonObject().get("state"));
            }
        }
    }
}
