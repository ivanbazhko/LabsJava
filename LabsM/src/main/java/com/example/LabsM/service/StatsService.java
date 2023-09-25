package com.example.LabsM.service;

import com.example.LabsM.entity.ListStatistics;
import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {
    public ListStatistics getAllStats(List<Response> list) {
        ListStatistics stats = new ListStatistics();
        List<Triangle> clearList = new ArrayList<>();
        list.forEach(a -> {
            if(a.getError() == null) clearList.add(a.getTriangle());
        });
        if(!clearList.isEmpty()) {
            stats.setAvgArea(getAverageArea(clearList));
            stats.setAvgPerimeter(getAveragePerimeter(clearList));
            stats.setMinArea(getMinArea(clearList));
            stats.setMinPerimeter(getMinPerimeter(clearList));
            stats.setMaxArea(getMaxArea(clearList));
            stats.setMaxPerimeter(getMaxPerimeter(clearList));
            Double maxSide1 = getMaxSide1(clearList);
            Double minSide1 = getMinSide1(clearList);
            Double avgSide1 = getAvgSide1(clearList);
            Double maxSide2 = getMaxSide2(clearList);
            Double minSide2 = getMinSide2(clearList);
            Double avgSide2 = getAvgSide2(clearList);
            Double maxSide3 = getMaxSide3(clearList);
            Double minSide3 = getMinSide3(clearList);
            Double avgSide3 = getAvgSide3(clearList);
            stats.setAvgLength((avgSide1 + avgSide2 + avgSide3) / 3);
            stats.setMaxLength(Math.max(maxSide1, Math.max(maxSide2, maxSide3)));
            stats.setMinLength(Math.min(minSide1, Math.min(minSide2, minSide3)));
            stats.setMaxSize1(maxSide1);
            stats.setMinSize1(minSide1);
            stats.setAvgSize1(avgSide1);
            stats.setMaxSize2(maxSide2);
            stats.setMinSize2(minSide2);
            stats.setAvgSize2(avgSide2);
            stats.setMaxSize3(maxSide3);
            stats.setMinSize3(minSide3);
            stats.setAvgSize3(avgSide3);
            stats.setAmountCounted(clearList.size());
            stats.setAmountFailed(list.size() - clearList.size());
        };
        return stats;
    }
    public Double getAverageArea(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getArea).average().getAsDouble();
    }
    public Double getAveragePerimeter(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getPerimeter).average().getAsDouble();
    }
    public Double getMinArea(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getArea).min().getAsDouble();
    }
    public Double getMinPerimeter(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getPerimeter).min().getAsDouble();
    }
    public Double getMaxArea(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getArea).max().getAsDouble();
    }
    public Double getMaxPerimeter(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getPerimeter).max().getAsDouble();
    }
    public Double getMaxSide1(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide1).max().getAsDouble();
    }
    public Double getMinSide1(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide1).min().getAsDouble();
    }
    public Double getAvgSide1(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide1).average().getAsDouble();
    }
    public Double getMaxSide2(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide2).max().getAsDouble();
    }
    public Double getMinSide2(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide2).min().getAsDouble();
    }
    public Double getAvgSide2(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide2).average().getAsDouble();
    }
    public Double getMaxSide3(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide3).max().getAsDouble();
    }
    public Double getMinSide3(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide3).min().getAsDouble();
    }
    public Double getAvgSide3(List<Triangle> list) {
        return list.stream().mapToDouble(Triangle::getSide3).average().getAsDouble();
    }
}
