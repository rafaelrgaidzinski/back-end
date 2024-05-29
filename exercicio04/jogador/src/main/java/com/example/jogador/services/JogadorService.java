package com.example.jogador.services;

import com.example.jogador.models.*;
import com.example.jogador.repositories.JogadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class JogadorService {

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    RequisicaoService requisicaoService;

    @Transactional
    public JogadorModel save(JogadorModel jogadorModel) {return jogadorRepository.save(jogadorModel);}

    @Transactional
    public void delete(JogadorModel jogadorModel) { jogadorRepository.delete(jogadorModel);}

    public Optional<JogadorModel> findById(UUID id) {return jogadorRepository.findById(id);}

    public List<JogadorModel> findAll() {return jogadorRepository.findAll();}

    public String convertModelToJson(JogadorModel jogadorModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jogadorModel);
    }

    public JogadorModel gerarJogador() {

        Random random = new Random();

        List<NomeModel> listaNomes = requisicaoService.findAllNomes();
        List<SobrenomeModel> listaSobrenomes = requisicaoService.findAllSobrenomes();
        List<PosicaoModel> listaPosicoes = requisicaoService.findAllPosicoes();
        List<ClubeModel> listaClubes = requisicaoService.findAllClubes();

        int indexNomes = random.nextInt(listaNomes.size());
        int indexSobrenomes = random.nextInt(listaSobrenomes.size());
        int indexClubes = random.nextInt(listaClubes.size());
        int indexPosicoes = random.nextInt(listaPosicoes.size());
        int idade = random.nextInt(24) + 17;

        NomeModel nomeModel = listaNomes.get(indexNomes);
        SobrenomeModel sobrenomeModel = listaSobrenomes.get(indexSobrenomes);
        ClubeModel clubeModel = listaClubes.get(indexClubes);
        PosicaoModel posicaoModel = listaPosicoes.get(indexPosicoes);

        String nome = nomeModel.getNome().charAt(0) + nomeModel.getNome().substring(1).toLowerCase();
        String sobrenome = sobrenomeModel.getSobrenome().charAt(0) + sobrenomeModel.getSobrenome().substring(1).toLowerCase();
        String clube = clubeModel.getClube();
        String posicao = posicaoModel.getPosicao().replaceAll("\"","").replaceAll(",","");

        return new JogadorModel(nome,sobrenome,clube,posicao,idade);
    }
}
