package org.example;

import java.text.MessageFormat;

public class Jogador {

    String nome;
    String sobrenome;
    String clube;
    String posicao ;
    int idade;

    public Jogador(String nome, String sobrenome, String posicao, String clube, int idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.posicao = posicao;
        this.clube = clube;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getIdade() {
        return idade;
    }

    public String getClube() {
        return clube;
    }

    public String getDescricao() {
        return MessageFormat.format("{0} {1} é um futebolista brasileiro de {2} anos que atua como {3}. Atualmente defende o {4}.", this.nome, this.sobrenome, this.idade, this.posicao, this.clube);
    }
}
