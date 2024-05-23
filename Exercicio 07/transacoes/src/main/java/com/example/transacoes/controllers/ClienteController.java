package com.example.transacoes.controllers;

import com.example.transacoes.dtos.ClienteDto;
import com.example.transacoes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDto> getAllClientes(@RequestParam(defaultValue = "0") Integer pagina,
                                           @RequestParam(defaultValue = "5") Integer resultados,
                                           @RequestParam(defaultValue = "nome") List<String> ordenacao
    ) {
        return clienteService.getClientesPaginados(pagina, resultados, ordenacao);
    }

    @GetMapping("/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto getClienteByNome(@PathVariable String nome) {
        return  clienteService.getClienteByNome(nome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto getClienteById(@PathVariable UUID id) {
        return  clienteService.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto saveCliente(@RequestBody ClienteDto clienteDto){
        return clienteService.saveCliente(clienteDto);
    }

}
