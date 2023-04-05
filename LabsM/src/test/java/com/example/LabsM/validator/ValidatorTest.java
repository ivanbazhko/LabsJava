package com.example.LabsM.validator;

import com.example.LabsM.entity.Triangle;
import com.example.LabsM.entity.TriangleError;
import com.example.LabsM.validator.TriangleValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    private TriangleValidator validator = new TriangleValidator();
    @Test
    public void testNotNull() {
        String num1 = "12", num2 = "5", num3 = "13";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertNotNull(result);
    }
    @Test
    public void testRight() {
        String num1 = "12", num2 = "5", num3 = "13";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertEquals("", result.getMessage());
        assertEquals("", result.getStatus());
    }
    @Test
    public void testWrongExist() {
        String num1 = "12", num2 = "5", num3 = "25";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertEquals("Triangle doesn't exist!", result.getMessage());
        assertEquals("BAD_REQUEST", result.getStatus());
    }
    @Test
    public void testWrongBlank() {
        String num1 = "12", num2 = "", num3 = "25";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertEquals("Found empty field!", result.getMessage());
        assertEquals("BAD_REQUEST", result.getStatus());
    }
    @Test
    public void testWrongNegative() {
        String num1 = "12", num2 = "5", num3 = "-13";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertEquals("Length can't be negative or zero!", result.getMessage());
        assertEquals("BAD_REQUEST", result.getStatus());
    }
    @Test
    public void testWrongNotNumber() {
        String num1 = "qwerty", num2 = "5", num3 = "13";
        TriangleError result = validator.validateInput(num1, num2, num3);
        assertEquals("Length must be a number!", result.getMessage());
        assertEquals("BAD_REQUEST", result.getStatus());
    }
}
