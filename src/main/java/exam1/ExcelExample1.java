package exam1;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
