package donga2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;

public class ExcelDto {
    static final int START_ROW_NUMBER = 3;

    JsonParser jsonParser = new JsonParser();
    Workbook workbook = new SXSSFWorkbook();
    Sheet sheet = workbook.createSheet();
    int rowIndex = START_ROW_NUMBER;


    public void searchFilePath(String filePath) throws FileNotFoundException {
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
            JsonElement fileName = parse.getAsJsonObject().get("id");
            // 05-01 => 0~3
            for (JsonElement element : utterance) {
                String label = element.getAsJsonObject().get("label").getAsString();
                String[] labels = label.split(",");
                for (String str : labels){
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
            System.out.println(
                    String.format(
                            "%s  / %s  : 오발화 : %d , 반복발화 : %d , 간투어 : %d , 단절발화 : %d"
                            ,file.getName(),fileName,utterance_o2,utterance_b3,utterance_g4,utterance_d1)
            );

            // excel createCell => 2부터
            // 2~5 , 6~9 , 10~13
        }
    }


}
