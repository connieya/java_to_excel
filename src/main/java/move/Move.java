package move;

import java.io.File;

public class Move {
    public static void main(String[] args) {
        File target = new File("D:\\1_분절(after)\\0_그룹별_분류\\temp");
        File dir = new File("D:\\1_분절(after)\\0_그룹별_분류\\copy");

        File[] listFiles = target.listFiles();
        for (File file : listFiles) {
            File moveFile = new File(dir, file.getName());
            file.renameTo(moveFile);
        }
        System.out.println("작업이 완료되었습니다.");

    }
}
