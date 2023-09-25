package com.example.LabsM.jpa;

import com.example.LabsM.jpa.model.IdModel;
import com.example.LabsM.jpa.model.TriangleWithId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdJpaRepository extends JpaRepository<IdModel, Integer> {
}
