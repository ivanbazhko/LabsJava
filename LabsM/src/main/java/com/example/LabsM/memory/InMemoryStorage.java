package com.example.LabsM.memory;

import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import com.example.LabsM.entity.TriangleNumericParams;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryStorage {
    private Map<TriangleNumericParams, Triangle> storage = new HashMap<TriangleNumericParams, Triangle>();
    public void addData(Triangle triangle) {
        storage.put(triangle.getNumericParams(), triangle);
    }
    public void addData(List<Response> list) {
        list.forEach(a -> {
            if(a.getError() == null) {
                storage.put(a.getTriangle().getNumericParams(), a.getTriangle());
            }
        });
    }
    public void addData3(List<Triangle> list) {
        list.forEach(a -> {
            if(a != null) {
                storage.put(a.getNumericParams(), a);
            }
        });
    }
    public Triangle getTriangle(Double id) {
        return storage.get(id);
    }
    public List<Triangle> getAllData() {
        List<Triangle> dataList = new ArrayList<Triangle>();
        storage.forEach((a, b) -> dataList.add(b));
        return dataList;
    }
    public Integer getSize() {
        return storage.size();
    }
    public Boolean contains(TriangleNumericParams params) {
        return storage.containsKey(params);
    }
    public Triangle getTriangleByKey(TriangleNumericParams params) {
        return storage.get(params);
    }
}
