package com.example.oscar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ACTORS")
public class ActorModel implements Eligible, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private Boolean eligible;
    @Column(nullable = false)
    private Short numberOfIndications;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String nationality;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Boolean getEligible() {
        return eligible;
    }

    @Override
    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    @Override
    public Short getNumberOfIndications() {
        return numberOfIndications;
    }

    @Override
    public void setNumberOfIndications(Short numberOfIndications) {
        this.numberOfIndications = numberOfIndications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
