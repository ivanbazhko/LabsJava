package com.example.LabsM.entity;

public class Triangle {
    private double side1;
    private double side2;
    private double side3;
    private double perimeter;
    private double area;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = 0;
        this.area = 0;
    }
    public Triangle(double side1, double side2, double side3, double perimeter, double area) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = perimeter;
        this.area = area;
    }
    public Triangle() {
        this.side1 = 0;
        this.side2 = 0;
        this.side3 = 0;
        this.perimeter = 0;
        this.area = 0;
    }
    public double getSide1(){
        return this.side1;
    }
    public double getSide2(){
        return this.side2;
    }
    public double getSide3(){
        return this.side3;
    }
    public double getPerimeter(){
        return this.perimeter;
    }
    public double getArea(){
        return this.area;
    }
    public void setSide1(double length){
        this.side1 = length;
    }
    public void setSide2(double length){
        this.side2 = length;
    }
    public void setSide3(double length){
        this.side3 = length;
    }
    public void setPerimeter(double length){
        this.perimeter = length;
    }
    public void setArea(double newArea){
        this.area = newArea;
    }
}
