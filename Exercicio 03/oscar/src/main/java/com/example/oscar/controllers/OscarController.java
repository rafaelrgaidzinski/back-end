package com.example.oscar.controllers;

import com.example.oscar.dtos.AtorRecordDto;
import com.example.oscar.dtos.FilmeRecordDto;
import com.example.oscar.dtos.IndicacaoRecordDto;
import com.example.oscar.models.AtorModel;
import com.example.oscar.models.FilmeModel;
import com.example.oscar.models.IndicacaoModel;
import com.example.oscar.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oscar")
@Scope("prototype")
public class OscarController {

    @Autowired
    OscarService oscarService;

    @PostMapping("/filme")
    public ResponseEntity<FilmeModel> saveFilme(@RequestBody @Valid FilmeRecordDto filmeRecordDto){
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeRecordDto, filmeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(oscarService.adicionarFilme(filmeModel));
    }

    @PostMapping("/ator")
    public ResponseEntity<AtorModel> saveAtor(@RequestBody @Valid AtorRecordDto atorRecordDto){
        var atorModel = new AtorModel();
        BeanUtils.copyProperties(atorRecordDto, atorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(oscarService.adicionarAtor(atorModel));
    }

    @PostMapping("/indicacao")
    public ResponseEntity<Object> saveIndicacao(@RequestBody @Valid IndicacaoRecordDto indicacaoRecordDto){
        var indicacaoModel = new IndicacaoModel();
        BeanUtils.copyProperties(indicacaoRecordDto, indicacaoModel);

        boolean resultadoIndicacao = oscarService.adicionarIndicacao(indicacaoModel);

        if (resultadoIndicacao) {
            return ResponseEntity.status(HttpStatus.CREATED).body(indicacaoModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível realizar a indicacao, pois o indicável não é elegível");
    }

    @GetMapping("/indicacoes")
    public ResponseEntity<List<IndicacaoModel>> getIndicacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(oscarService.mostrarListaIndicacoes());
    }

}
