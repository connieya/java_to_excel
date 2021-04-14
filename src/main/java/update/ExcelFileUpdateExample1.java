package update;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelFileUpdateExample1 {

    public static void main(String[] args) {
        String excelFilePath = "C:\\summernote/JavaBooks.xls";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Object[][] bookData = {

                    {"The Passionate Programmer", "Chad  Fowler", 16},
                    {"Software Craftmanship", "Pete", 26},
                    {"the art", "james", 32},
                    {"continous", "jez", 41},
            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] aBook : bookData) {
                Row  row = sheet.createRow(++rowCount);

                int columnCount = 0;

                Cell cell =  row.createCell(columnCount);
                cell.setCellValue(rowCount);

                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field  instanceof String){
                        cell.setCellValue((String) field);
                    }else if(field instanceof Integer){
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("C:\\summernote/JavaBooks.xls");

            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
