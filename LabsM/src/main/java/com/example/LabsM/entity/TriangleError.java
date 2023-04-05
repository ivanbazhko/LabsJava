package com.example.LabsM.entity;

public class TriangleError {
    private String message;
    private String status;
    public TriangleError() {
        this.message = "";
        this.status = "";
    }
    public TriangleError(String message, String status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage(){
        return this.message;
    }
    public String getStatus(){
        return this.status;
    }
    public void setMessage(String newMessage){
        this.message = newMessage;
    }
    public void setStatus(String newStatus){
        this.status = newStatus;
    }
}
