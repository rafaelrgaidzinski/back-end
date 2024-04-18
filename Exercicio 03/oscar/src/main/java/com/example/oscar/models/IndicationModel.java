package com.example.oscar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_INDICATIONS")
public class IndicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private UUID eligibleId;
    @Column(nullable = false, length = 50)
    private String category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEligibleId() {
        return eligibleId;
    }

    public void setEligibleId(UUID eligibleId) {
        this.eligibleId = eligibleId;
    }

    public String getCategoria() {
        return category;
    }

    public void setCategoria(String category) {
        this.category = category;
    }
}
