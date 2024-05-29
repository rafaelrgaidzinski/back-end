package com.example.transferencias.controllers;

import com.example.transferencias.dtos.TransferenciaRecordDto;
import com.example.transferencias.models.TransferenciaModel;
import com.example.transferencias.services.TransferenciaService;
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
@RequestMapping("/transferencias")
@Scope("singleton")
public class TransferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<TransferenciaModel> saveTransferencia(@RequestBody @Valid TransferenciaRecordDto transferenciaRecordDto){
        var transferenciaModel = new TransferenciaModel();
        BeanUtils.copyProperties(transferenciaRecordDto, transferenciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.save(transferenciaModel));
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaModel>> getTransferencias() {
        return ResponseEntity.status(HttpStatus.OK).body(transferenciaService.findAll());
    }

    @GetMapping("/{transferenciaId}")
    public ResponseEntity<Object> getTransferencia(@PathVariable(value = "transferenciaId") UUID transferenciaId) {
        Optional<TransferenciaModel> transferenciaModelOptional = transferenciaService.findById(transferenciaId);
        if(transferenciaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferência não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transferenciaModelOptional.get());
    }

    @PutMapping("/{transferenciaId}")
    public ResponseEntity<Object> updateTransferencia(@PathVariable(value = "transferenciaId") UUID transferenciaId) {
        Optional<TransferenciaModel> transferenciaModelOptional = transferenciaService.findById(transferenciaId);
        if(transferenciaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferência não encontrada.");
        }
        var transferenciaModel = new TransferenciaModel();
        BeanUtils.copyProperties(transferenciaModelOptional.get(), transferenciaModel);
        transferenciaModel.setId(transferenciaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(transferenciaService.save(transferenciaModel));
    }

    @DeleteMapping("/{transferenciaId}")
    public ResponseEntity<Object> deleteTransferencia(@PathVariable(value = "transferenciaId") UUID transferenciaId) {
        Optional<TransferenciaModel> transferenciaModelOptional = transferenciaService.findById(transferenciaId);
        if(transferenciaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferência não encontrada.");
        }
        transferenciaService.delete(transferenciaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transferência excluída com sucesso.");
    }
}
