package com.example.LabsM.controller;

import com.example.LabsM.annotation.CustomExceptionHandler;
import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import com.example.LabsM.entity.TriangleError;
import com.example.LabsM.exception.InternalServerException;
import com.example.LabsM.memory.InMemoryStorage;
import com.example.LabsM.service.TriangleService;
import com.example.LabsM.validator.TriangleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/lab")
@CustomExceptionHandler
public class TriangleController {
    private static final Logger logger = LoggerFactory.getLogger(TriangleController.class);
    private TriangleService triangleService;
    private TriangleValidator triangleValidator;
    private InMemoryStorage inMemoryStorage;
    public TriangleController(TriangleService service, TriangleValidator validator, InMemoryStorage storage) {
        this.triangleService = service;
        this.triangleValidator = validator;
        this.inMemoryStorage = storage;
    }
    @GetMapping("/task")
    public ResponseEntity<Object> Calculations(@RequestParam String side1, @RequestParam String side2,
                                 @RequestParam String side3) {
        Response response = new Response();
        logger.info("Validation");
        TriangleError validationResponse = triangleValidator.validateInput(side1, side2, side3);
        if (!validationResponse.getMessage().isBlank()) {
            logger.error("INVALID INPUT!");
            response.setError(validationResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (Double.parseDouble(side3) != 20) {
            logger.info("Calculations");
            Triangle triangle = triangleService.calculate(Double.parseDouble(side1),
                    Double.parseDouble(side2), Double.parseDouble(side3));
            response.setTriangle(triangle);
            response.setErrorStatus(HttpStatus.OK.name());
            logger.info("Adding Cache");
            inMemoryStorage.addData(triangle);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            throw new InternalServerException("Internal Server Error!");
        }
    }
    @GetMapping("/data")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getData() {
        logger.info("Getting Cache");
        return new ResponseEntity<>(inMemoryStorage.getAllData(), HttpStatus.OK);
    }
}
