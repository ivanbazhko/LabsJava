package com.example.LabsM.validator;
import com.example.LabsM.controller.TriangleController;
import com.example.LabsM.entity.TriangleError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TriangleValidator {
    private static final Logger logger = LoggerFactory.getLogger(TriangleController.class);
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
}
