package com.example.LabsM.entity;

public class TriangleParams {
    private String side1;
    private String side2;
    private String side3;

    public TriangleParams(String side1, String side2, String side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public String getSide1(){
        return this.side1;
    }
    public String getSide2(){
        return this.side2;
    }
    public String getSide3(){
        return this.side3;
    }
    public void setSide1(String length){
        this.side1 = length;
    }
    public void setSide2(String length){
        this.side2 = length;
    }
    public void setSide3(String length){
        this.side3 = length;
    }
}
