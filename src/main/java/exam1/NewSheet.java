package exam1;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewSheet {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("C:\\summernote/JavaBooks.xls");

            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet newSheet = workbook.createSheet("Comments");
            Object[][] bookComments = {
                    {"Head First Java", "Funny and Exciting"},
                    {"Effective Java", "Insightful tips and advices"},
                    {"Clean Code", "Write Readable Code"},
                    {"Thinking in Java", "Classic"},
            };

            int rowCount = 0;

            for (Object[] aBook : bookComments){
                Row row = newSheet.createRow(++rowCount);

                int columnCount = 0;

                for (Object field : aBook){
                    Cell cell = row.createCell(++columnCount);
                    if (field instanceof String){
                        cell.setCellValue((String) field);
                    }else if (field instanceof Integer){
                        cell.setCellValue((Integer)field);
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream("C:\\summernote/JavaBooks.xls");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch ( IOException  e) {
            e.printStackTrace();
        }

    }
}
