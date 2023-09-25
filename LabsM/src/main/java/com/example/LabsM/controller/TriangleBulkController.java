package com.example.LabsM.controller;

import com.example.LabsM.annotation.CustomExceptionHandler;
import com.example.LabsM.entity.*;
import com.example.LabsM.exception.InternalServerException;
import com.example.LabsM.memory.InMemoryStorage;
import com.example.LabsM.service.*;
import com.example.LabsM.validator.TriangleValidator;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/lab")
@CustomExceptionHandler
public class TriangleBulkController {
    private static final Logger logger = LoggerFactory.getLogger(TriangleBulkController.class);
    private TriangleService triangleService;
    private TriangleValidator triangleValidator;
    private InMemoryStorage inMemoryStorage;
    private CountService countService;
    private UnSyncCountService unSyncCountService;
    private StatsService statsService;
    private DataBaseService dbService;
    public TriangleBulkController(TriangleService service, TriangleValidator validator, InMemoryStorage storage,
                                  CountService countService, UnSyncCountService unSyncCountService,
                                  StatsService statsService, DataBaseService dbService) {
        this.triangleService = service;
        this.triangleValidator = validator;
        this.inMemoryStorage = storage;
        this.countService = countService;
        this.unSyncCountService = unSyncCountService;
        this.statsService = statsService;
        this.dbService = dbService;
    }
    @PostMapping("/task")
    public ResponseEntity<Object> countAllTriangles(@RequestBody List<TriangleParams> paramsList) {
        ListResponse bulkCalculationsResult = new ListResponse();
        List<Response> result = triangleValidator.validateMultipleTriangles(paramsList);
        List<Response> finalResult = triangleService.calculateList(result);
        inMemoryStorage.addData(finalResult);
        bulkCalculationsResult.setResult(finalResult);
        dbService.saveAllTriangles(bulkCalculationsResult.getResult());
        bulkCalculationsResult.setStatistics(statsService.getAllStats(bulkCalculationsResult.getResult()));
       return new ResponseEntity<>(bulkCalculationsResult, HttpStatus.CREATED);
    }
    @GetMapping("/database/list")
    public ResponseEntity<Object> getDb() {
        List<Triangle> response = dbService.getAllTriangles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/database/cache")
    public ResponseEntity<Object> enCacheDb() {
        List<Triangle> response = dbService.getAllTriangles();
        inMemoryStorage.addData3(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
