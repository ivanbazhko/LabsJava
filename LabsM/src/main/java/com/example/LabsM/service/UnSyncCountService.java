package com.example.LabsM.service;

import com.example.LabsM.controller.TriangleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UnSyncCountService {
    private static final Logger logger = LoggerFactory.getLogger(UnSyncCountService.class);
    private static int number = 0;
    public void increment(){
        number++;
    }
    public int getNumber(){
        return number;
    }
    public void reset() {
        this.number = 0;
    }
}
