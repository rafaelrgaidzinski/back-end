package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Random;

public class JogadorGerador {

    String[] nomes;
    String[] sobrenomes;
    String[] clubes;
    String[] posicoes;

    public void gerarJogador() {

        Random random = new Random();

        int indexNomes = random.nextInt(this.nomes.length);
        int indexSobrenomes = random.nextInt(this.sobrenomes.length);
        int indexClubes = random.nextInt(this.clubes.length);
        int indexPosicoes = random.nextInt(this.posicoes.length);

        int idade = random.nextInt(24) + 17;
        String nome = this.nomes[indexNomes].substring(0,1) + this.nomes[indexNomes].substring(1).toLowerCase();
        String sobrenome = this.sobrenomes[indexSobrenomes].substring(0,1) + this.sobrenomes[indexSobrenomes].substring(1).toLowerCase();
        String clube = this.clubes[indexClubes];
        String posicao = this.posicoes[indexPosicoes].replaceAll("\"","").replaceAll(",","");
  
        Jogador jogador = new Jogador(nome, sobrenome, posicao, clube, idade);
        System.out.println(jogador.getDescricao());
    }

    public void buscarInformacoes(Map<String, String> uris) throws IOException, InterruptedException {

        for (Map.Entry<String, String> entry : uris.entrySet()) {

            String[] listaInformacao = requisicao(entry.getValue());

            switch (entry.getKey()) {
                case "posicoes":
                    this.posicoes = listaInformacao;
                    break;
                case "nomes":
                    this.nomes = listaInformacao;
                    break;
                case "sobrenomes":
                    this.sobrenomes = listaInformacao;
                    break;
                case "clubes":
                    this.clubes = listaInformacao;
                    break;
                default:
                    // code block
            }
        }
    }

    public static String[] requisicao(String path) throws IOException, InterruptedException {

        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma requisição HTTP
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).build();

        // Resposta da requisição
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String[] listaInformacoes = response.body().split("\n");

        return listaInformacoes;
    }
}