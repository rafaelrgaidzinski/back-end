package com.example.oscar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_indicacoes")
public class IndicacaoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private Indicavel indicavel;
    @Column(nullable = false, length = 50)
    private String categoria;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Indicavel getIndicavel() {
        return indicavel;
    }

    public void setIndicavel(Indicavel indicavel) {
        this.indicavel = indicavel;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
