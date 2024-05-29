package com.example.oscar.controllers;

import com.example.oscar.dtos.IndicacaoRecordDto;
import com.example.oscar.models.IndicacaoModel;
import com.example.oscar.services.IndicacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/indicacoes")
@Scope("prototype")
public class IndicacaoController {

    @Autowired
    IndicacaoService indicacaoService;

    @PostMapping
    public ResponseEntity<Object> saveIndicacao(@RequestBody @Valid IndicacaoRecordDto indicacaoRecordDto){
        var indicacaoModel = new IndicacaoModel();
        BeanUtils.copyProperties(indicacaoRecordDto, indicacaoModel);

        boolean resultadoIndicacao = indicacaoService.adicionarIndicacao(indicacaoModel);

        if (resultadoIndicacao) {
            return ResponseEntity.status(HttpStatus.CREATED).body(indicacaoService.save(indicacaoModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível realizar a indicacao, pois o indicável não é elegível");
    }

    @GetMapping
    public ResponseEntity<List<IndicacaoModel>> getIndicacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(indicacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIndicacao(@PathVariable(value = "id") UUID id) {
        Optional<IndicacaoModel> indicacaoModelOptional = indicacaoService.findById(id);
        if(indicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indicação não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(indicacaoModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateIndicacao(@PathVariable(value = "id") UUID id) {
        Optional<IndicacaoModel> indicacaoModelOptional = indicacaoService.findById(id);
        if(indicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indicação não encontrada");
        }

        var indicacaoModel = new IndicacaoModel();
        BeanUtils.copyProperties(indicacaoModelOptional.get(), indicacaoModel);
        indicacaoModel.setId(indicacaoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(indicacaoService.save(indicacaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIndicacao(@PathVariable(value = "id") UUID id) {
        Optional<IndicacaoModel> indicacaoModelOptional = indicacaoService.findById(id);
        if(indicacaoModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indicação não encontrada");
        }
        indicacaoService.delete(indicacaoModelOptional.get());
        indicacaoService.cancelarIndicacao(indicacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indicação excluída com sucesso");
    }

}
