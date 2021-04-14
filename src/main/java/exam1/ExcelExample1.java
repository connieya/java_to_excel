package exam1;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExample1 {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("C:\\summernote/JavaBooks.xls");
            System.out.println("inputStream = " +inputStream);

            Workbook workbook = WorkbookFactory.create(inputStream);
            System.out.println("workbook = " +workbook);

            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("sheet = " +sheet);

            int rowCount = sheet.getLastRowNum();
            System.out.println("rowCount = " +rowCount);

            Row row = sheet.getRow(3);

            System.out.println("row = " +row);

            Cell cell = row.getCell(1);
            System.out.println("cell = " +cell);

            Row rowData = sheet.getRow(0);
            Cell cell1 = rowData.getCell(2);

            System.out.println("Cell1 = " +cell1);

            Cell cell1update =  sheet.getRow(7).getCell(2);
            cell1update.setCellValue("gunny");
            System.out.println("cell1update = " +cell1update);

            workbook.setSheetName(0,"Programming Book");

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("C:\\summernote/JavaBooks.xls");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (InvalidFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
