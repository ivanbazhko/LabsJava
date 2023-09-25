package com.example.LabsM.jpa;

import com.example.LabsM.jpa.model.TriangleWithId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriangleJpaRepository extends JpaRepository<TriangleWithId, Integer> {
}
