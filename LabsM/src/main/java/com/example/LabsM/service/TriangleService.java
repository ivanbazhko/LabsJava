package com.example.LabsM.service;
import com.example.LabsM.controller.TriangleController;
import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import com.example.LabsM.memory.InMemoryStorage;
import com.example.LabsM.validator.TriangleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TriangleService {
    private static final Logger logger = LoggerFactory.getLogger(TriangleService.class);
    private InMemoryStorage inMemoryStorage;
    private CountService countService;
    public TriangleService(CountService countService, InMemoryStorage inMemoryStorage) {
        this.countService = countService;
        this.inMemoryStorage = inMemoryStorage;
    }
    public Triangle calculate(double side1, double side2, double side3){
        logger.info("Calculating one");
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setPerimeter(side1 + side2 + side3);
        double halfPerimeter = (side1 + side2 + side3) / 2;
        triangle.setArea(Math.sqrt(halfPerimeter * (halfPerimeter - side1) *
                (halfPerimeter - side2) * (halfPerimeter - side3)));
        return triangle;
    };
    public Triangle calculate(Triangle triangle){
        logger.info("Calculating one");
        Double side1 = triangle.getSide1();
        Double side2 = triangle.getSide2();
        Double side3 = triangle.getSide3();
        triangle.setPerimeter(side1 + side2 + side3);
        double halfPerimeter = (side1 + side2 + side3) / 2;
        triangle.setArea(Math.sqrt(halfPerimeter * (halfPerimeter - side1) *
                (halfPerimeter - side2) * (halfPerimeter - side3)));
        return triangle;
    }
    public List<Response> calculateList(List<Response> listToDo) {
        Triangle triangle = new Triangle();
        listToDo.forEach(a -> {
            if(a.getError() == null) {
                if(inMemoryStorage.contains(a.getTriangle().getNumericParams())){
                    logger.info("Taking Triangle From Storage");
                    a.setTriangle(inMemoryStorage.getTriangleByKey(a.getTriangle().getNumericParams()));
                } else {
                    a.setTriangle(calculate(a.getTriangle()));
                    logger.info("Calculating many");
                    countService.increment();
                }
            }
        });
        return listToDo;
    }
}