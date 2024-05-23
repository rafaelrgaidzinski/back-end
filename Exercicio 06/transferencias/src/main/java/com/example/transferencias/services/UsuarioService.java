package com.example.transferencias.services;

import com.example.transferencias.models.UsuarioModel;
import com.example.transferencias.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    @Transactional
    public void delete(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }
}
