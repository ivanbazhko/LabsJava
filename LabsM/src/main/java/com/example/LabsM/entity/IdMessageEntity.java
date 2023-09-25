package com.example.LabsM.entity;

public class IdMessageEntity {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IdMessageEntity(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    private Integer id;
    private String message;
}
