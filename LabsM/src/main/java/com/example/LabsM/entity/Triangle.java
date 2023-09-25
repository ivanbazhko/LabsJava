package com.example.LabsM.entity;

import com.example.LabsM.jpa.model.TriangleWithId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Triangle {
    private Double side1;
    private Double side2;
    private Double side3;
    private Double perimeter;
    private Double area;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = (double) 0;
        this.area = (double) 0;
    }
    public Triangle(double side1, double side2, double side3, double perimeter, double area) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = perimeter;
        this.area = area;
    }
    public Triangle(TriangleWithId triangle) {
        this.side1 = triangle.getSide1();
        this.side2 = triangle.getSide2();
        this.side3 = triangle.getSide3();
        this.perimeter = triangle.getPerimeter();
        this.area = triangle.getArea();
    }
    public Triangle() {
        this.side1 = (double) 0;
        this.side2 = (double) 0;
        this.side3 = (double) 0;
        this.perimeter = (double) 0;
        this.area = (double) 0;
    }
    public Double getSide1(){
        return this.side1;
    }
    public Double getSide2(){
        return this.side2;
    }
    public Double getSide3(){
        return this.side3;
    }
    public Double getPerimeter(){
        return this.perimeter;
    }
    public Double getArea(){
        return this.area;
    }
    public void setSide1(Double length){
        this.side1 = length;
    }
    public void setSide2(Double length){
        this.side2 = length;
    }
    public void setSide3(Double length){
        this.side3 = length;
    }
    public void setPerimeter(Double length){
        this.perimeter = length;
    }
    public void setArea(Double newArea){
        this.area = newArea;
    }
    @JsonIgnore
    public TriangleNumericParams getNumericParams() {
        return new TriangleNumericParams(this.side1, this.side2, this.side3);
    }
}
