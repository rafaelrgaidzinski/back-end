package com.example.jogador.controllers;

import com.example.jogador.models.JogadorModel;
import com.example.jogador.services.JogadorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/gerarJogador")
@Scope("prototype")
public class JogadorController {

    @Autowired
    JogadorService jogadorService;

    @GetMapping("/mensagem")
    public ResponseEntity<Object> gerarMensagemJogador() {
        Optional<JogadorModel> jogadorModelOptional = Optional.ofNullable(jogadorService.gerarJogador());
        if(jogadorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível gerar o jogador.");
        }

        JogadorModel jogadorModel = new JogadorModel();
        BeanUtils.copyProperties(jogadorModelOptional.get(), jogadorModel);
        jogadorService.save(jogadorModel);

        String mensagem = MessageFormat.format("{0} {1} é um futebolista brasileiro de {2} anos que atua como {3}. Atualmente defende o {4}.", jogadorModel.getNome(), jogadorModel.getSobrenome(), jogadorModel.getIdade(), jogadorModel.getPosicao(), jogadorModel.getClube());
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }

    @GetMapping("/json")
    public ResponseEntity<Object> gerarJsonJogador() throws JsonProcessingException {
        Optional<JogadorModel> jogadorModelOptional = Optional.ofNullable(jogadorService.gerarJogador());
        if(jogadorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível gerar o jogador.");
        }

        JogadorModel jogadorModel = new JogadorModel();
        BeanUtils.copyProperties(jogadorModelOptional.get(), jogadorModel);
        jogadorService.save(jogadorModel);

        String jsonJogador = jogadorService.convertModelToJson(jogadorModel);
        return ResponseEntity.status(HttpStatus.OK).body(jsonJogador);
    }



}
