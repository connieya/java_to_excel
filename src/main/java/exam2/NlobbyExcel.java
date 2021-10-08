package exam2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NlobbyExcel {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\summernote/exam.xls");

        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Cell cell = sheet.getRow(20).getCell(3);

        Cell cell1 = sheet.getRow(20).getCell(1);
        Cell cell2 = sheet.getRow(19).getCell(1);


        System.out.println("cell = " +cell);

        System.out.println("cell1 = " +cell1);
        System.out.println("cell2 = " +cell2);

        Cell cell3 = sheet.getRow(20).getCell(4);
        Cell visit = sheet.getRow(21).getCell(1);
        System.out.println("방문 현황 글자 : " + visit);

        Cell cell4 = sheet.getRow(22).getCell(1);
        System.out.println("차량 방문객 신청현황 = " +cell3);
        System.out.println("방문현황 방문 = " +cell4);

        Cell cell5 = sheet.getRow(22).getCell(2);
        System.out.println("방문현황 미방문 = " + cell5);

        Cell cell6 = sheet.getRow(27).getCell(2);
        System.out.println("체류시간 인원 평균 : " +cell6);
        Cell cell7 = sheet.getRow(27).getCell(6);

        System.out.println("sms : " +cell7);
//        cell1.setCellValue(5123);
//
//        inputStream.close();
//
//        FileOutputStream outputStream = new FileOutputStream("C:\\summernote/exam.xls");
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.close();
    }
}
