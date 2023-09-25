package com.example.LabsM.controller;

import com.example.LabsM.annotation.CustomExceptionHandler;
import com.example.LabsM.entity.*;
import com.example.LabsM.service.CountService;
import com.example.LabsM.exception.InternalServerException;
import com.example.LabsM.memory.InMemoryStorage;
import com.example.LabsM.service.DataBaseService;
import com.example.LabsM.service.TriangleService;
import com.example.LabsM.service.UnSyncCountService;
import com.example.LabsM.validator.TriangleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/lab")
@CustomExceptionHandler
public class TriangleController {
    private static final Logger logger = LoggerFactory.getLogger(TriangleController.class);
    private TriangleService triangleService;
    private TriangleValidator triangleValidator;
    private InMemoryStorage inMemoryStorage;
    private CountService countService;
    private UnSyncCountService unSyncCountService;
    private DataBaseService dbService;
    public TriangleController(TriangleService service, TriangleValidator validator, InMemoryStorage storage,
                              CountService countService, UnSyncCountService unSyncCountService,
                              DataBaseService dbService) {
        this.triangleService = service;
        this.triangleValidator = validator;
        this.inMemoryStorage = storage;
        this.countService = countService;
        this.unSyncCountService = unSyncCountService;
        this.dbService = dbService;
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
            Triangle triangle;
            if(inMemoryStorage.contains(new TriangleNumericParams(Double.parseDouble(side1),
                    Double.parseDouble(side2), Double.parseDouble(side3)))) {
                logger.info("Taking Triangle From Storage");
                triangle = inMemoryStorage.getTriangleByKey(new TriangleNumericParams(Double.parseDouble(side1),
                        Double.parseDouble(side2), Double.parseDouble(side3)));
            } else {
                logger.info("Calculating");
                triangle = triangleService.calculate(Double.parseDouble(side1),
                        Double.parseDouble(side2), Double.parseDouble(side3));
                logger.info("Adding Cache");
                inMemoryStorage.addData(triangle);
            }
            if(dbService.checkInDb(triangle) == 0) {
                dbService.saveOneTriangle(triangle);
            }
            countService.increment();
            //unSyncCountService.increment();
            response.setTriangle(triangle);
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
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getCount() {
        logger.info("Getting Count");
        AmountEntity result = new AmountEntity(countService.getNumber());
        //AmountEntity result = new AmountEntity(unSyncCountService.getNumber());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/countReset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> resetCount() {
        logger.info("Resetting Counter");
        AmountEntity result = new AmountEntity(countService.getNumber());
        //AmountEntity result = new AmountEntity(unSyncCountService.getNumber());
        countService.reset();
        unSyncCountService.reset();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/database/getById")
    public ResponseEntity<Object> getById(@RequestParam Integer id) {
        if(id > dbService.getLastId()) return new ResponseEntity<>("Wrong id", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dbService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/asyncTask")
    public ResponseEntity<Object> AsyncCalculations(@RequestParam String side1, @RequestParam String side2,
                                               @RequestParam String side3) {
        logger.info("Validation");
        TriangleError validationResponse = triangleValidator.validateInput(side1, side2, side3);
        if (!validationResponse.getMessage().isBlank()) {
            logger.error("INVALID INPUT!");
            return new ResponseEntity<>(new IdMessageResponse(null, validationResponse), HttpStatus.BAD_REQUEST);
        }
        Integer dbCheck;
        if((dbCheck = dbService.checkInDb(new Triangle(Double.parseDouble(side1),
                Double.parseDouble(side2), Double.parseDouble(side3)))) != 0) {
            return new ResponseEntity(new IdMessageResponse(new IdMessageEntity(dbCheck,"Triangle is already in database"),
                    null), HttpStatus.OK);
        }
        Integer newId = dbService.getNewId();
        CompletableFuture<Void> futureSave = CompletableFuture.runAsync(() ->
                dbService.saveOneTriangleById(triangleService.calculate(Double.parseDouble(side1),
                        Double.parseDouble(side2), Double.parseDouble(side3)), newId));
        logger.info("Returning message");
        return new ResponseEntity(new IdMessageResponse(new IdMessageEntity(newId,"Triangle will be in database"),
                null), HttpStatus.OK);
    }
}
