package donga2;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class FileFactoryTest {

    @Test
    void fileFactory() {
        Map<String, Integer> fileKey = FileFactory.fileKey;
        System.out.println(fileKey);
    }
}
