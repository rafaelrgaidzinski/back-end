package com.example.oscar.models;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;


public interface Indicavel {
    @OneToOne
    public short getNumeroDeIndicacoes();
    @OneToOne
    void setNumeroDeIndicacoes(short numeroDeIndicacoes);
    @OneToOne
    public Boolean isElegivel();


}
