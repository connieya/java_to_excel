package donga2;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;

public class ExcelDto {
    JsonParser jsonParser = new JsonParser();
    Workbook workbook = new SXSSFWorkbook();
    Sheet sheet = workbook.createSheet();
    int rowIndex = 3;

    public void SearchFilePath(String filePath) throws FileNotFoundException {
        File path = new File(filePath);
        File[] files = path.listFiles();
        for (File file : files) {
            sheet.createRow(rowIndex++).createCell(0).setCellValue(file.getName());
            searchJsonFiles(file);
        }
    }

    private void searchJsonFiles(File file) throws FileNotFoundException {
        File jsonFiles = new File(file.getPath());
        File[] jsonList = jsonFiles.listFiles();
        for (File json : jsonList) {
            String ext = FilenameUtils.getExtension(json.getName());
            String id = json.getName().substring(0, 5);
            if (ext.equals("json") && id.compareTo("05-00") > 0) {
                Reader reader = new FileReader(json);
                JsonElement parse = jsonParser.parse(reader);
                if (parse.isJsonObject()) {
                    JsonElement element = parse.getAsJsonObject().get("utterance");
                    JsonElement fileName = parse.getAsJsonObject().get("id");
                    System.out.println(file.getName() + ",  " + fileName + " : " + element);
                }
            }
        }
    }

}
