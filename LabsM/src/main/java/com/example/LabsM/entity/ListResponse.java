package com.example.LabsM.entity;

import java.util.ArrayList;
import java.util.List;

public class ListResponse {
    private List<Response> result = new ArrayList<>();
    private ListStatistics statistics;
    public List<Response> getResult() {
        return result;
    }
    public void setResult(List<Response> result) {
        this.result = result;
    }
    public ListStatistics getStatistics() {
        return statistics;
    }
    public void setStatistics(ListStatistics statistics) {
        this.statistics = statistics;
    }
    public void addNewResponse(Response newResponse){
        this.result.add(newResponse);
    }
}
