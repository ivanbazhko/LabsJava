package com.example.LabsM.service;

import com.example.LabsM.entity.Triangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    private TriangleService service = new TriangleService();
    @Test
    public void testNotNull() {
        double num1 = 12, num2 = 5, num3 = 13;
        Triangle result = service.calculate(num1, num2, num3);
        assertNotNull(result);
    }
    @Test
    public void testEquals() {
        double num1 = 12, num2 = 5, num3 = 13;
        Triangle result = service.calculate(num1, num2, num3);
        assertEquals(30, result.getPerimeter());
        assertEquals(30, result.getArea());
    }
}
