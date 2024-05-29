package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        Map<String, String> uris = new HashMap<String, String>();
        uris.put("clubes", "https://venson.net.br/resources/data/clubes.txt");
        uris.put("posicoes", "https://venson.net.br/resources/data/posicoes.txt");
        uris.put("sobrenomes", "https://venson.net.br/resources/data/sobrenomes.txt");
        uris.put("nomes", "https://venson.net.br/resources/data/nomes.txt");

        String nome = null;
        String sobrenome = null;
        String clube = null;
        String posicao = null;
        int idade = 0;

        for (Map.Entry<String, String> entry : uris.entrySet()) {

            String[] listaInformacao = requisicao(entry.getValue());

            Random random = new Random();

            idade = random.nextInt(24) + 17;
            int numeroAleatorio = random.nextInt(listaInformacao.length);

            switch (entry.getKey()) {
                case "posicoes":
                    posicao = listaInformacao[numeroAleatorio].replaceAll("\"","").replaceAll(",","");
                    break;
                case "nomes":
                    nome = listaInformacao[numeroAleatorio].substring(0,1) + listaInformacao[numeroAleatorio].substring(1).toLowerCase();
                    break;
                case "sobrenomes":
                    sobrenome = listaInformacao[numeroAleatorio].substring(0,1) + listaInformacao[numeroAleatorio].substring(1).toLowerCase();
                    break;
                case "clubes":
                    clube = listaInformacao[numeroAleatorio];
                    break;
                default:
                    // code block
            }
        }

        String mensagem = MessageFormat.format("{0} {1} é um futebolista brasileiro de {2} anos que atua como {3}. Atualmente defende o {4}.", nome, sobrenome, idade, posicao, clube);

        System.out.println(mensagem);
    }

    static String[] requisicao(String path) throws IOException, InterruptedException {

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