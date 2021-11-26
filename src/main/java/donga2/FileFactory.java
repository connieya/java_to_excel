package donga2;

import java.util.HashMap;
import java.util.Map;

public class FileFactory {
    static Map<String ,Integer> fileKey = new HashMap<>();
    static int fileSeq[] = {30,30,10};
    static int index =2;

    static {
        for (int i=0; i<3; i++){
            for (int j=1; j<=fileSeq[i]; j++) {
                String key = String.format("0%d-%02d", i + 5, j);
                fileKey.put(key,index);
                index += 4;
            }
        }
    }

}
