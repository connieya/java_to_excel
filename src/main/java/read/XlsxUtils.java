package read;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsxUtils {

    @SuppressWarnings("resource")
    public static List<List<String>> readToList(String path) {
        List<List<String>> list = new ArrayList<>();

        try {
            FileInputStream fi = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fi);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null){
                    List<String > cellList = new ArrayList<>();
                    for (int j=0; j<row.getLastCellNum(); j++){
                        XSSFCell cell = row.getCell(j);
                        if (cell != null){
                            cellList.add(cellReader(cell));
                        }
                    }
                    list.add(cellList);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("imcomplete-switch")
    private static String cellReader(XSSFCell cell) {
        String value = "";
        CellType ct = cell.getCellTypeEnum();
        if (ct != null){
            switch (cell.getCellTypeEnum()){
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    value = cell.getNumericCellValue()+"";
                    break;
                case STRING:
                    value = cell.getStringCellValue()+"";
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue()+"";
                case ERROR:
                    value = cell.getErrorCellValue()+"";
                    break;
            }
        }
        return value;

    }
}
