package com.example.transacoes.controllers;

import com.example.transacoes.dtos.TransacaoDto;
import com.example.transacoes.dtos.clienteDtos.ClienteRequestDto;
import com.example.transacoes.dtos.clienteDtos.ClienteResponseDto;
import com.example.transacoes.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDto> saveTransacao(@RequestBody @Valid TransacaoDto transacaoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.save(transacaoDto));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDto>> getTransacao(@RequestParam(defaultValue = "0") Integer pagina,
                                                          @RequestParam(defaultValue = "5") Integer resultados,
                                                          @RequestParam(defaultValue = "pagador") List<String> ordenacao
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.findAllPageable(pagina, resultados, ordenacao)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransacaoById(@PathVariable(value = "id") UUID id) {
        Optional<TransacaoDto> optionalTransacaoDto = transacaoService.findById(id);
        if (optionalTransacaoDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalTransacaoDto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransacao(@PathVariable(value = "id") UUID id){
        Optional<TransacaoDto> optionalTransacaoDto = transacaoService.findById(id);
        if (optionalTransacaoDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada.");
        }
        transacaoService.delete(optionalTransacaoDto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transação excluída com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransacao(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid TransacaoDto transacaoDto){
        Optional<TransacaoDto> optionalTransacaoDto = transacaoService.findById(id);
        if (optionalTransacaoDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.save(optionalTransacaoDto.get()));
    }
}
