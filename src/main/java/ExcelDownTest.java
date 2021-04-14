import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;

public class ExcelDownTest {
    public static void main(String[] args) {

        XSSFWorkbook xssfWb = null;
        XSSFSheet xssfSheet = null;
        XSSFRow xssfRow = null;
        XSSFCell xssfCell = null;

        int rowNo = 0;

        xssfWb = new XSSFWorkbook();
        xssfSheet = xssfWb.createSheet("워크 시트1");

        XSSFFont font = xssfWb.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short)20);
        font.setBold(true);
        font.setColor(new XSSFColor(Color.decode("#457ba2")));

        CellStyle cellStyle = xssfWb.createCellStyle();
        xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0)) +(short)2048);

        cellStyle.setFont(font);

        xssfSheet.addMergedRegion(new CellRangeAddress(0,1,0,4));

        xssfRow = xssfSheet.createRow(rowNo++);
        xssfCell = xssfRow.createCell((short)0);
        xssfCell.setCellStyle(cellStyle);
        xssfCell.setCellValue("타이틀 입니다");

        xssfSheet.createRow(rowNo++);
        xssfRow = xssfSheet.createRow(rowNo++);

        CellStyle tableCellStyle = xssfWb.createCellStyle();
        tableCellStyle.setBorderTop(BorderStyle.valueOf((short)5));
        tableCellStyle.setBorderBottom(BorderStyle.valueOf((short)5));
        tableCellStyle.setBorderLeft(BorderStyle.valueOf((short)5));
        tableCellStyle.setBorderRight(BorderStyle.valueOf((short)5));

        xssfRow = xssfSheet.createRow(rowNo++);
        xssfCell = xssfRow.createCell((short) 0);
        xssfCell.setCellStyle(tableCellStyle);



    }
}
