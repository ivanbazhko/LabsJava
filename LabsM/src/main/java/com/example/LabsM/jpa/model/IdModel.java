package com.example.LabsM.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "id_table")
public class IdModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public IdModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IdModel(Integer id) {
        this.id = id;
    }
}
