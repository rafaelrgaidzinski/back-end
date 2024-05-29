package com.example.oscar.controllers;

import com.example.oscar.dtos.AtorRecordDto;
import com.example.oscar.models.AtorModel;
import com.example.oscar.services.AtorService;
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
@RequestMapping("/atores")
@Scope("prototype")
public class AtorController {

    @Autowired
    AtorService atorService;

    @PostMapping
    public ResponseEntity<AtorModel> saveAtor(@RequestBody @Valid AtorRecordDto atorRecordDto){
        var atorModel = new AtorModel();
        BeanUtils.copyProperties(atorRecordDto, atorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(atorService.save(atorModel));
    }

    @GetMapping
    public ResponseEntity<List<AtorModel>> getAtores() {
        return ResponseEntity.status(HttpStatus.OK).body(atorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAtor(@PathVariable(value = "id") UUID id) {
        Optional<AtorModel> atorModelOptional = atorService.findById(id);
        if(atorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(atorModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtor(@PathVariable(value = "id") UUID id) {
        Optional<AtorModel> atorModelOptional = atorService.findById(id);
        if(atorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator não encontrado.");
        }
        var atorModel = new AtorModel();
        BeanUtils.copyProperties(atorModelOptional.get(), atorModel);
        atorModel.setId(atorModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(atorService.save(atorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAtor(@PathVariable(value = "id") UUID id) {
        Optional<AtorModel> atorModelOptional = atorService.findById(id);
        if(atorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator não encontrado.");
        }
        atorService.delete(atorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ator excluído com sucesso.");
    }

}
