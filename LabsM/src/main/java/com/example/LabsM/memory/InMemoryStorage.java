package com.example.LabsM.memory;

import com.example.LabsM.entity.Triangle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryStorage {
    private Map<Double, Triangle> storage = new HashMap<Double, Triangle>();
    public void addData(Triangle triangle) {
        storage.put(triangle.getArea(), triangle);
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
}
