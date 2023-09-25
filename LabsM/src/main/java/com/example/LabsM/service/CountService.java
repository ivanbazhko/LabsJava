package com.example.LabsM.service;

import com.example.LabsM.controller.TriangleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountService {
    private static final Logger logger = LoggerFactory.getLogger(CountService.class);
    private AtomicInteger number = new AtomicInteger(0);
    public void increment(){
        number.getAndIncrement();
        logger.info("Incrementing Counter");
    }
    public int getNumber(){
        return number.get();
    }
    public void reset() {
        this.number.set(0);
    }
}