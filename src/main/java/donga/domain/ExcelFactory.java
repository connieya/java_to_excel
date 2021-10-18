package donga.domain;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelFactory {

    public static void createExcelHead(XSSFSheet sheet) {
        XSSFRow initRow = sheet.createRow(0);
        initRow.createCell(0).setCellValue("filePath");
        initRow.createCell(1).setCellValue("id");
        initRow.createCell(2).setCellValue("ID(REPLACE)");
        initRow.createCell(3).setCellValue("Target1");
        initRow.createCell(4).setCellValue("Target2");
        initRow.createCell(5).setCellValue("age");
        initRow.createCell(6).setCellValue("gender");
        initRow.createCell(7).setCellValue("state");
        initRow.createCell(8).setCellValue("record_date");
        initRow.createCell(9).setCellValue("bitratio");
        initRow.createCell(10).setCellValue("seq");
        initRow.createCell(11).setCellValue("start");
        initRow.createCell(12).setCellValue("end");
        initRow.createCell(13).setCellValue("label");
        initRow.createCell(14).setCellValue("note");
    }

    public static void setColumnWidth(XSSFSheet sheet) {
        sheet.setColumnWidth(0, 13500);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 2500);
        sheet.setColumnWidth(4, 2500);
        sheet.setColumnWidth(6, 2500);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 2500);
        sheet.setColumnWidth(11, 15000);
        sheet.setColumnWidth(12, 15000);
        sheet.setColumnWidth(13, 15000);
        sheet.setColumnWidth(14, 8000);
    }
}
