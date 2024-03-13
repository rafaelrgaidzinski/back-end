package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Map<String, String> uris = new HashMap<String, String>();
        uris.put("clubes", "https://venson.net.br/resources/data/clubes.txt");
        uris.put("posicoes", "https://venson.net.br/resources/data/posicoes.txt");
        uris.put("sobrenomes", "https://venson.net.br/resources/data/sobrenomes.txt");
        uris.put("nomes", "https://venson.net.br/resources/data/nomes.txt");

        JogadorGerador jogadorGerador = new JogadorGerador();

        jogadorGerador.buscarInformacoes(uris);

        Scanner inputUsuario = new Scanner(System.in);
        System.out.println("Quantos jogadores você deseja gerar: ");

        int quantidadeJogadores = inputUsuario.nextInt();

        for (int i = 0; i < quantidadeJogadores; i++) {
            jogadorGerador.gerarJogador();
        }

        inputUsuario.close();
    }

}