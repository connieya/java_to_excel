package donga2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.Map;

public class ExcelDto {
    static final int START_ROW_NUMBER = 3;
    JsonParser jsonParser = new JsonParser();
    Workbook workbook = new SXSSFWorkbook();
    Sheet sheet = workbook.createSheet();
    int rowIndex = START_ROW_NUMBER;
    Map<String, Integer> fileKey;
    Row row;

    public ExcelDto() {
        fileKey = FileFactory.fileKey;
        workbook.createCellStyle().setAlignment(HorizontalAlignment.LEFT);
    }

    public void searchFilePath(String filePath) throws FileNotFoundException {
        File path = new File(filePath);
        File[] files = path.listFiles();
        for (File file : files) {
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(file.getName());
            searchJsonFiles(file);
        }
    }

    private void searchJsonFiles(File file) throws FileNotFoundException {
        File jsonFiles = new File(file.getPath());
        File[] jsonList = jsonFiles.listFiles();
        for (File json : jsonList) {
            readJsonFile(file, json);
        }
    }

    private void readJsonFile(File file, File json) throws FileNotFoundException {
        String ext = FilenameUtils.getExtension(json.getName());
        String id = json.getName().substring(0, 5);
        if (ext.equals("json") && id.compareTo("05-00") > 0) {
            Reader reader = new FileReader(json);
            JsonElement parse = jsonParser.parse(reader);
            if (!parse.isJsonObject()) {
                return;
            }
            int utterance_o2 = 0;
            int utterance_b3 = 0;
            int utterance_g4 = 0;
            int utterance_d1 = 0;
            JsonArray utterance = parse.getAsJsonObject().get("utterance").getAsJsonArray();
            String fileName = parse.getAsJsonObject().get("id").getAsString().substring(0, 5);
            // 05-01 => 0~3
            for (JsonElement element : utterance) {
                String label = element.getAsJsonObject().get("label").getAsString();
                String[] labels = label.split(",");
                for (String str : labels) {
                    String trim = str.trim();
                    if (trim.equals("오발화")) {
                        utterance_o2++;
                    }
                    if (trim.equals("반복발화")) {
                        utterance_b3++;

                    }
                    if (trim.equals("간투어")) {
                        utterance_g4++;
                    }
                    if (trim.equals("단절발화")) {
                        utterance_d1++;
                    }
                }
            }
            int firstIndex = fileKey.getOrDefault(fileName, 0);
            System.out.println(
                    String.format(
                            "%s  / %s  : 오발화 : %d , 반복발화 : %d , 간투어 : %d , 단절발화 : %d , %d"
                            , file.getName(), fileName, utterance_o2, utterance_b3, utterance_g4, utterance_d1, firstIndex)
            );
            if (firstIndex != 0) {
                row.createCell(firstIndex).setCellValue(utterance_d1);
                row.createCell(firstIndex + 1).setCellValue(utterance_b3);
                row.createCell(firstIndex + 2).setCellValue(utterance_o2);
                row.createCell(firstIndex + 3).setCellValue(utterance_g4);
            }
        }
    }


}
