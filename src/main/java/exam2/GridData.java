package exam2;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class GridData {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\summernote/exam.xls");

        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Row row1 = sheet.getRow(39);
        Cell cell = row1.getCell(0);
        System.out.println("cell = " +cell);

        for (int i = 0; i<=30; i++){
            Cell cell1 = sheet.getRow(i+40).getCell(1);
            System.out.println("cell1 = " + cell1);
        }

    }
}
