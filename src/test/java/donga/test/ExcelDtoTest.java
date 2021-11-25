package donga.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelDtoTest {

    @Test
    void compare() {
        Assertions.assertTrue("03-10".compareTo( "04-01") < 0);
    }

    @Test
    void length() {
        System.out.println("01-01");
    }

    @Test
    void trim() {
        String a = "단절발화";

    }
}