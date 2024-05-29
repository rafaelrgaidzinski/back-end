package com.example.transacoes.controllers;

import com.example.transacoes.dtos.clienteDtos.ClienteRequestDto;
import com.example.transacoes.dtos.clienteDtos.ClienteResponseDto;
import com.example.transacoes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> saveCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> getClientes(@RequestParam(defaultValue = "0") Integer pagina,
                                                                @RequestParam(defaultValue = "5") Integer resultados,
                                                                @RequestParam(defaultValue = "nome") List<String> ordenacao
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllPageable(pagina, resultados, ordenacao)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable(value = "id") UUID id) {
        Optional<ClienteResponseDto> optionalClienteResponseDto = clienteService.findClienteResponseById(id);
        if (optionalClienteResponseDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalClienteResponseDto.get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getClienteByNome(@PathVariable(value = "nome") String nome) {
        Optional<List<ClienteResponseDto>> optionalClienteResponseDtoList = clienteService.findByNome(nome);
        optionalClienteResponseDtoList.stream().forEach(System.out::println);
        if (optionalClienteResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar clientes com este nome");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalClienteResponseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id){
        Optional<ClienteRequestDto> optionalClienteRequestDto = clienteService.findClienteRequestById(id);
        if (optionalClienteRequestDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        clienteService.delete(optionalClienteRequestDto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ClienteRequestDto clienteRequestDto){
        Optional<ClienteRequestDto> optionalClienteRequestDto = clienteService.findClienteRequestById(id);
        if (optionalClienteRequestDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(optionalClienteRequestDto.get()));
    }
}
