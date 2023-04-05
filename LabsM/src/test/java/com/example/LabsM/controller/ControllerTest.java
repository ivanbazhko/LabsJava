package com.example.LabsM.controller;

import com.example.LabsM.memory.InMemoryStorage;
import com.example.LabsM.service.TriangleService;
import com.example.LabsM.validator.TriangleValidator;
import com.example.LabsM.entity.TriangleError;
import com.example.LabsM.entity.Response;
import com.example.LabsM.exception.InternalServerException;
import com.example.LabsM.entity.Triangle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class ControllerTest {
    private TriangleService service = mock(TriangleService.class);
    private TriangleValidator validator = mock(TriangleValidator.class);
    private InMemoryStorage storage = mock(InMemoryStorage.class);
    @InjectMocks
    private TriangleController triangleController = new TriangleController(service, validator, storage);
    @Test
    public void testCalculations() {
        String num1 = "12", num2 = "5", num3 = "13";
        double num4 = 12, num5 = 5, num6 = 13, perimeter = 30, area = 30;
        Triangle triangle = new Triangle(num4, num5, num6, perimeter, area);
        TriangleError error = new TriangleError();

        when(service.calculate(num4, num5, num6)).thenReturn(triangle);
        when(validator.validateInput(num1, num2, num3)).thenReturn(error);

        ResponseEntity<Object> result = triangleController.Calculations(num1, num2, num3);
        Triangle resTriangle = ((Response)result.getBody()).getTriangle();
        assertNotNull(result);
        assertEquals(30, resTriangle.getArea());
    }
    @Test
    public void testException() throws InternalServerException {
        String num1 = "19", num2 = "5", num3 = "20";
        double num4 = 19, num5 = 5, num6 = 20;
        Triangle triangle = new Triangle(num4, num5, num6);
        TriangleError error = new TriangleError();
        when(service.calculate(num4, num5, num6)).thenReturn(triangle);
        when(validator.validateInput(num1, num2, num3)).thenReturn(error);
        try {
            ResponseEntity<Object> result = triangleController.Calculations(num1, num2, num3);
        } catch (InternalServerException exception) {
            assertNotNull(exception.getMessage());
        }
    }
    @Test
    public void testValidation() {
        String num1 = "19", num2 = "5", num3 = "-21";
        double num4 = 19, num5 = 5, num6 = -21;
        Triangle triangle = new Triangle(num4, num5, num6);
        TriangleError error = new TriangleError("Length can't be negative or zero!",
                HttpStatus.INTERNAL_SERVER_ERROR.name());

        when(service.calculate(num4, num5, num6)).thenReturn(triangle);
        when(validator.validateInput(num1, num2, num3)).thenReturn(error);

        ResponseEntity<Object> result = triangleController.Calculations(num1, num2, num3);
    }
    @Test
    public void testCache() {
        String num1 = "12", num2 = "5", num3 = "13";
        double num4 = 12, num5 = 5, num6 = 13, num7 = 30, num8 = 30;
        Triangle triangle = new Triangle(num4, num5, num6, num7, num8);
        TriangleError error = new TriangleError();
        List<Triangle> resTr = new ArrayList<>();
        resTr.add(triangle);

        when(service.calculate(num4, num5, num6)).thenReturn(triangle);
        when(validator.validateInput(num1, num2, num3)).thenReturn(error);
        when(storage.getAllData()).thenReturn(resTr);

        triangleController.Calculations(num1, num2, num3);
        ResponseEntity<Object> result = triangleController.getData();
        List<Triangle> resList = (List<Triangle>)result.getBody();
        assertNotNull(result);
        assertEquals(30, resList.get(0).getPerimeter());
    }
}
