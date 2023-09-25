package com.example.LabsM.entity;

import java.util.Objects;

public class TriangleNumericParams {
    private Double side1;
    private Double side2;
    private Double side3;

    public TriangleNumericParams(Double side1, Double side2, Double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Double getSide1() {
        return side1;
    }

    public void setSide1(Double side1) {
        this.side1 = side1;
    }

    public Double getSide2() {
        return side2;
    }

    public void setSide2(Double side2) {
        this.side2 = side2;
    }

    public Double getSide3() {
        return side3;
    }

    public void setSide3(Double side3) {
        this.side3 = side3;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        final TriangleNumericParams compObj = (TriangleNumericParams) obj;
        if(Double.compare(this.side1, compObj.getSide1()) == 0 && Double.compare(this.side2, compObj.getSide2()) == 0
                && Double.compare(this.side3, compObj.getSide3()) == 0) return true;
        else return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3);
    }
}
