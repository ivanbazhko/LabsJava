package com.example.LabsM.service;
import com.example.LabsM.entity.Triangle;
import org.springframework.stereotype.Service;

@Service
public class TriangleService {
    public Triangle calculate(double side1, double side2, double side3){
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setPerimeter(side1 + side2 + side3);
        double halfPerimeter = (side1 + side2 + side3) / 2;
        triangle.setArea(Math.sqrt(halfPerimeter * (halfPerimeter - side1) *
                (halfPerimeter - side2) * (halfPerimeter - side3)));
        return triangle;
    };
}