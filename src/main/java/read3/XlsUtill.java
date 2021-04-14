package read3;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XlsUtill {
    private static float lat[];
    private static float lng[];
    private final static int NUM = 24;

    public XlsUtill() {
    }

    public static void run(String path) {

        try {

        FileInputStream fis = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);

        int rowIndex = 0;
        int columnIndex = 0;
        //시트  수(첫번째에만 존재 하므로 0을 준다)
        // 만약 각 시트를 읽기 위해서는 FOR 문을 한번 더 돌려준다.

        // 행의 수
        int rows = sheet.getPhysicalNumberOfRows();
        for (rowIndex = 1; rowIndex < rows; rowIndex++) {
            //행을 읽는다.
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                // 셀의 수
                int cells = row.getPhysicalNumberOfCells();
                for (columnIndex = 0; columnIndex <= cells; columnIndex++) {
                    //셀값을 읽는다.
                    HSSFCell cell = sheet.getRow(rowIndex).getCell((short) columnIndex);
                    String value = "";
                    //셀이 빈값일경우를 위한 널체크
                    if (cell == null) {
                        continue;
                    } else {
                        //타입별로 내용 읽기
                       value = callReader(cell);
                    }
                        System.out.println(rowIndex+"번 행 : "+columnIndex+"번 열 값은: " + value);
                }
            }
        }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static String callReader(HSSFCell cell) {
        String value = "";
        switch (cell.getCellType()) {
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case NUMERIC:
                value = cell.getNumericCellValue() + "";
                break;
            case STRING:
                value = cell.getStringCellValue() + "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
            case ERROR:
                value = cell.getErrorCellValue() + "";
                break;

        }
        return value;
    }
}
