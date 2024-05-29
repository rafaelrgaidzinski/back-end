package com.example.oscar.controllers;

import com.example.oscar.dtos.FilmeRecordDto;
import com.example.oscar.models.FilmeModel;
import com.example.oscar.services.FilmeService;
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
@RequestMapping("/filmes")
@Scope("prototype")
public class FilmeControler {

    @Autowired
    FilmeService filmeService;

    @PostMapping
    public ResponseEntity<FilmeModel> saveFilme(@RequestBody @Valid FilmeRecordDto filmeRecordDto){
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeRecordDto, filmeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }

    @GetMapping
    public ResponseEntity<List<FilmeModel>> getFilmes() {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFilme(@PathVariable(value = "id") UUID id) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if(filmeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFilme(@PathVariable(value = "id") UUID id) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if(filmeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado.");
        }
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeModelOptional.get(), filmeModel);
        filmeModel.setId(filmeModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.save(filmeModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFilme(@PathVariable(value = "id") UUID id) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if(filmeModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado.");
        }
        filmeService.delete(filmeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Filme excluído com sucesso.");
    }

}
