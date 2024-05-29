package com.example.oscar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_atores")
public class AtorModel implements Indicavel, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String nomeAtor;
    @Column(nullable = false, length = 50)
    private String nacionalidade;
    @Column(nullable = false)
    private Boolean elegivel;
    @Column(nullable = false)
    private Short numeroDeIndicacoes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeAtor() {
        return nomeAtor;
    }

    public void setNomeAtor(String nome) {
        this.nomeAtor = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setElegivel(Boolean elegivel) {
        this.elegivel = elegivel;
    }

    @Override
    public Boolean isElegivel() {
        return elegivel;
    }

    @Override
    public short getNumeroDeIndicacoes() {
        return numeroDeIndicacoes;
    }

    @Override
    public void setNumeroDeIndicacoes(short numeroDeIndicacoes) {
        this.numeroDeIndicacoes = numeroDeIndicacoes;
    }


}
