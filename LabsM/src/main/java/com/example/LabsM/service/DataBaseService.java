package com.example.LabsM.service;

import com.example.LabsM.controller.TriangleController;
import com.example.LabsM.entity.Response;
import com.example.LabsM.entity.Triangle;
import com.example.LabsM.jpa.TriangleJpaRepository;
import com.example.LabsM.jpa.IdJpaRepository;
import com.example.LabsM.jpa.model.TriangleWithId;
import com.example.LabsM.jpa.model.IdModel;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseService.class);
    private TriangleJpaRepository repository;
    private IdJpaRepository idRepository;
    public DataBaseService(TriangleJpaRepository repository, IdJpaRepository idRepository) {
        this.repository = repository;
        this.idRepository = idRepository;
    }
    public void saveOneTriangle(Triangle triangle) {
        IdModel newId = new IdModel();
        idRepository.save(newId);
        TriangleWithId triangleForDB = new TriangleWithId(getLastId(), triangle.getSide1(), triangle.getSide2(),
                triangle.getSide3(), triangle.getPerimeter(), triangle.getArea());
        repository.save(triangleForDB);
        logger.info("Saving triangle");
    }
    public void saveAllTriangles(List<Response> list) {
        list.forEach(a -> {
            if(a.getError() == null && checkInDb((a.getTriangle())) == 0) saveOneTriangle(a.getTriangle());
        });
    }
    public List<Triangle> getAllTriangles() {
        List<Triangle> result = new ArrayList<>();
        repository.findAll().forEach(a -> {
            result.add(new Triangle(a));
        });
        return result;
    }
    public Integer checkInDb(Triangle triangle) {
        TriangleWithId triangleForDB = new TriangleWithId(triangle.getSide1(), triangle.getSide2(),
                triangle.getSide3(), (double) 0, (double) 0);
        List<TriangleWithId> list = repository.findAll();
        Long amount = repository.count();
        for(int i = 0; i < amount; i++) {
            if(Double.compare(list.get(i).getSide1(), triangle.getSide1()) == 0 &&
                    Double.compare(list.get(i).getSide2(), triangle.getSide2()) == 0 &&
                    Double.compare(list.get(i).getSide3(), triangle.getSide3()) == 0) {
                return list.get(i).getId();
            }
        }
        return 0;
    }
    public Triangle getById(Integer id) {
        List<TriangleWithId> list = repository.findAll();
        Long amount = repository.count();
        for(int i = 0; i < amount; i++) {
            if(list.get(i).getId() == id) {
                return new Triangle(list.get(i));
            }
        }
        return null;
    }
    public Integer getLastId() {
        List<IdModel> list = idRepository.findAll();
        if(list.size() == 0) return 0;
        return (int)list.stream().mapToDouble(IdModel::getId).max().getAsDouble();
    }
    public Integer getNewId() {
        IdModel newId = new IdModel();
        idRepository.save(newId);
        List<IdModel> list = idRepository.findAll();
        if(list.size() == 0) return 0;
        return (int)list.stream().mapToDouble(IdModel::getId).max().getAsDouble();
    }
    public void saveOneTriangleById(Triangle triangle, Integer id) {
        TriangleWithId triangleForDB = new TriangleWithId(id, triangle.getSide1(), triangle.getSide2(),
                triangle.getSide3(), triangle.getPerimeter(), triangle.getArea());
        IdModel newId = new IdModel();
        repository.save(triangleForDB);
        logger.info("Saving triangle by id");
    }
}
