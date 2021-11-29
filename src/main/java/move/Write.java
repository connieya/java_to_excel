package move;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Write {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\82109\\Desktop\\text\\text.txt"));
        List<String> fileList = new ArrayList<>();
        String str;
        while ((str = reader.readLine()) != null){
            System.out.println(str);
        }
        reader.close();
    }
}
