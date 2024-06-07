package com.example.encurtador_link.baseConversion;

import com.example.encurtador_link.service.BaseConversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseConversionTest {

    private BaseConversion baseConversion;

    @BeforeEach
    void setUp() {
         baseConversion = new BaseConversion();
    }

    @Test
    public void testEncode() {

        long input = 12345L;
        String expectedOutput = "dnh";

        String actualOutput = baseConversion.encode(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDecode() {
        String input ="dnh";
        long expectedOutput = 12345L;

        long actualOutput = baseConversion.decode(input);

        assertEquals(expectedOutput, actualOutput);
    }

}
