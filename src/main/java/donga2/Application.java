package donga2;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ExcelDto excelDto = new ExcelDto();
        Sheet sheet= excelDto.sheet;
        ExcelFactory.initExcelHead(sheet);
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\summernote\\result.xlsx");
        String filePath = "C:\\Users\\82109\\Desktop\\data";
        excelDto.SearchFilePath(filePath);
        excelDto.workbook.write(fileOutputStream);
        System.out.println("json 데이터 엑셀 파일로 쓰기 완료");
        fileOutputStream.close();
    }
}
