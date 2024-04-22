package com.example.oscar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_filmes")
public class FilmeModel implements Indicavel, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String nomeFilme;
    @Column(nullable = false, length = 50)
    private String generoFilme;
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

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getGeneroFilme() {
        return generoFilme;
    }

    public void setGeneroFilme(String generoFilme) {
        this.generoFilme = generoFilme;
    }

    public void setElegivel(Boolean elegivel) {
        this.elegivel = elegivel;
    }

    @Override
    public Boolean isElegivel() { return elegivel; }

    @Override
    public short getNumeroDeIndicacoes() {
        return numeroDeIndicacoes;
    }

    @Override
    public void setNumeroDeIndicacoes(short numeroDeIndicacoes) {
        this.numeroDeIndicacoes = numeroDeIndicacoes;
    }
}
