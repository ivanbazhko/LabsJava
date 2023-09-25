package com.example.LabsM.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Response {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Triangle triangle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TriangleError error;
    public Response() {
        this.triangle = null;
        this.error = null;
    }
    public Response(Triangle triangle, TriangleError error) {
        this.triangle = triangle;
        this.error = error.getMessage().isBlank() ? null : error;
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
