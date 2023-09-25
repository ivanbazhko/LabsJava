package com.example.LabsM.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "triangles")
public class TriangleWithId {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "side1")
    private Double side1;
    @Column(name = "side2")
    private Double side2;
    @Column(name = "side3")
    private Double side3;

    public TriangleWithId(Integer id, Double side1, Double side2, Double side3, Double perimeter, Double area) {
        this.id = id;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = perimeter;
        this.area = area;
    }

    @Column(name = "perimeter")
    private Double perimeter;
    @Column(name = "area")
    private Double area;

    public TriangleWithId(Double side1, Double side2, Double side3, Double perimeter, Double area) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.perimeter = perimeter;
        this.area = area;
    }

    public TriangleWithId() {
        this.id = 0;
        this.side1 = (double) 0;
        this.side2 = (double) 0;
        this.side3 = (double) 0;
        this.perimeter = (double) 0;
        this.area = (double) 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
