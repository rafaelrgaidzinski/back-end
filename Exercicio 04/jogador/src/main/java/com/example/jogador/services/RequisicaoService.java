package com.example.jogador.services;

import com.example.jogador.models.*;
import com.example.jogador.repositories.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequisicaoService {

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        buscarInformacoes();
    }

    @Autowired
    ClubeRepository clubeRepository;

    @Autowired
    PosicaoRepository posicaoRepository;

    @Autowired
    NomeRepository nomeRepository;

    @Autowired
    SobrenomeRepository sobrenomeRepository;

    @Transactional
    public ClubeModel saveClube(ClubeModel clubeModel) {return clubeRepository.save(clubeModel);}

    public List<ClubeModel> findAllClubes() {
        return clubeRepository.findAll();
    }

    @Transactional
    public PosicaoModel savePosicao(PosicaoModel posicaoModel) {return posicaoRepository.save(posicaoModel);}

    public List<PosicaoModel> findAllPosicoes() {
        return posicaoRepository.findAll();
    }

    @Transactional
    public NomeModel saveNome(NomeModel nomeModel) {return nomeRepository.save(nomeModel);}

    public List<NomeModel> findAllNomes() {
        return nomeRepository.findAll();
    }

    @Transactional
    public SobrenomeModel saveSobrenome(SobrenomeModel sobrenomeModel) {return sobrenomeRepository.save(sobrenomeModel);}

    public List<SobrenomeModel> findAllSobrenomes() {
        return sobrenomeRepository.findAll();
    }

    public void buscarInformacoes() throws IOException, InterruptedException {

        Map<String, String> mapUris = new HashMap<>();
        mapUris.put("clubes", "https://venson.net.br/resources/data/clubes.txt");
        mapUris.put("posicoes", "https://venson.net.br/resources/data/posicoes.txt");
        mapUris.put("sobrenomes", "https://venson.net.br/resources/data/sobrenomes.txt");
        mapUris.put("nomes", "https://venson.net.br/resources/data/nomes.txt");

        for (Map.Entry<String, String> entry : mapUris.entrySet()) {

            String[] listaInformacao = requisicao(entry.getValue());

            switch (entry.getKey()) {
                case "posicoes":
                    for (String posicao : listaInformacao) {
                        if (posicao != null) {
                            PosicaoModel posicaoModel = new PosicaoModel(posicao);
                            savePosicao(posicaoModel);
                        }
                    }
                    break;
                case "nomes":
                    for (String nome : listaInformacao) {
                        if (nome != null) {
                            NomeModel nomeModel = new NomeModel(nome);
                            saveNome(nomeModel);
                        }
                    }
                    break;
                case "sobrenomes":
                    for (String sobrenome : listaInformacao) {
                        if (sobrenome != null) {
                            SobrenomeModel sobrenomeModel = new SobrenomeModel(sobrenome);
                            saveSobrenome(sobrenomeModel);
                        }
                    }
                    break;
                case "clubes":
                    for (String clube : listaInformacao) {
                        if (clube != null) {
                            ClubeModel clubeModel = new ClubeModel(clube);
                            saveClube(clubeModel);
                        }
                    }
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
