package com.example.LabsM.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class IdMessageResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IdMessageEntity message;

    public IdMessageResponse(IdMessageEntity message, TriangleError error) {
        this.message = message;
        this.error = error;
    }

    public IdMessageEntity getMessage() {
        return message;
    }

    public void setMessage(IdMessageEntity message) {
        this.message = message;
    }

    public TriangleError getError() {
        return error;
    }

    public void setError(TriangleError error) {
        this.error = error;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TriangleError error;
}
