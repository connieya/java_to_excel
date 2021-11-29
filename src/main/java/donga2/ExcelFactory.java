package donga2;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelFactory {
    // 1 :단절발화  2 : 반복  3:  오발화  4: 간투어
    static int dir[] = {30,30,10};
    static String utterances [] = {"" ,"단절발화","반복발화","오발화"," 간투어"};
    public static void initExcelHead(Sheet sheet){
        Row utterance = sheet.createRow(1);
        Row head = sheet.createRow(2);
        head.createCell(1).setCellValue("Sample");
        int count = 2;
        head.createCell(0).setCellValue("");
        for (int i=0; i<3; i++){
            for (int j=1; j<=dir[i]; j++) {
                for (int k=1; k<=4; k++){
                    utterance.createCell(count).setCellValue(utterances[k]);
                    head.createCell(count++).setCellValue(String.format("0%d-%02d_%d",i+5,j,k));
                }
            }
        }
    }

}
