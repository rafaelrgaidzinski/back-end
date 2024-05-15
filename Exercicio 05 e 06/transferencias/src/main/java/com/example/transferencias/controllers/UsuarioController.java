package com.example.transferencias.controllers;

import com.example.transferencias.dtos.UsuarioRecordDto;
import com.example.transferencias.models.UsuarioModel;
import com.example.transferencias.services.UsuarioService;
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
@RequestMapping("/usuarios")
@Scope("singleton")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "userId") UUID userId) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(userId);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "userId") UUID userId) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(userId);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioModelOptional.get(), usuarioModel);
        usuarioModel.setId(usuarioModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso.");
    }

}
