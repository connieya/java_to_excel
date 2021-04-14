package read;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath  = "C:\\summernote/ttt2.xlsx";
        List<List<String >> readList = XlsxUtils.readToList(filePath);

        for (int i = 0; i<readList.size(); i++){
            for (int j=0; j<readList.get(i).size(); j++){
                System.out.println("각 셀 내용 : "+readList.get(i).get(j));
            }
            System.out.println();
        }
    }
}
