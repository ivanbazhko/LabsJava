package com.example.LabsM.entity;

public class Response {
    private Triangle triangle;
    private TriangleError error;
    public Response(){
        this.triangle = new Triangle();
        this.error = new TriangleError();
    }
    public Triangle getTriangle(){
        return this.triangle;
    }
    public TriangleError getError(){
        return this.error;
    }
    public void setTriangle(Triangle newTriangle){
        this.triangle = newTriangle;
    }
    public void setError(TriangleError newError){
        this.error = newError;
    }
    public void setErrorStatus(String status) {
        this.error.setStatus(status);
    }
}
