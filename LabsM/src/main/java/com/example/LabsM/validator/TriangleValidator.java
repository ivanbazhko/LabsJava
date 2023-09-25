package com.example.LabsM.validator;
import com.example.LabsM.controller.TriangleController;
import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import com.example.LabsM.entity.TriangleError;
import com.example.LabsM.entity.TriangleParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TriangleValidator {
    private static final Logger logger = LoggerFactory.getLogger(TriangleValidator.class);
    public TriangleError validateInput(String len1, String len2, String len3) {
        TriangleError validationResponse = new TriangleError();
        if(len1.isBlank() || len2.isBlank() || len3.isBlank()) {
            validationResponse.setMessage("Found empty field!");
            logger.error("EMPTY FIELD!");
            validationResponse.setStatus(HttpStatus.BAD_REQUEST.name());
            return validationResponse;
        }
        try {
            Double.parseDouble(len1);
            Double.parseDouble(len2);
            Double.parseDouble(len3);
        }
        catch(NumberFormatException e) {
            validationResponse.setMessage("Length must be a number!");
            logger.error("NOT NUMERIC!");
            validationResponse.setStatus(HttpStatus.BAD_REQUEST.name());
            return validationResponse;
        }
        double lend1 = Double.parseDouble(len1);
        double lend2 = Double.parseDouble(len2);
        double lend3 = Double.parseDouble(len3);
        if(lend1 <= 0 || lend2 <= 0 || lend3 <= 0) {
            validationResponse.setMessage("Length can't be negative or zero!");
            logger.error("NOT POSITIVE!");
            validationResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        } else if((lend1 + lend2 <= lend3) || (lend2 + lend3 <= lend1) || (lend3 + lend1 <= lend2)) {
            validationResponse.setMessage("Triangle doesn't exist!");
            logger.error("WRONG TRIANGLE!");
            validationResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        }
        return validationResponse;
    }
    public List<Response> validateMultipleTriangles(List<TriangleParams> paramsList) {
        List<Response> result = new ArrayList<>();
        paramsList.forEach(a -> {
            TriangleError newError =  validateInput(a.getSide1(), a.getSide2(), a.getSide3());
            Triangle newTriangle = null;
            if(newError.getMessage().isBlank()) {
                newTriangle = new Triangle(Double.parseDouble(a.getSide1()),
                        Double.parseDouble(a.getSide2()), Double.parseDouble(a.getSide3()));
            }
            result.add(new Response(newTriangle, newError));
        });
        return result;
    }
}
