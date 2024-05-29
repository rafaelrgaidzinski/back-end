package com.example.transferencias.services;

import com.example.transferencias.models.TransferenciaModel;
import com.example.transferencias.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Transactional
    public TransferenciaModel save(TransferenciaModel transferenciaModel) {
        return transferenciaRepository.save(transferenciaModel);
    }

    @Transactional
    public void delete(TransferenciaModel transferenciaModel) {
        transferenciaRepository.delete(transferenciaModel);
    }

    public Optional<TransferenciaModel> findById(UUID id) {
        return transferenciaRepository.findById(id);
    }

    public List<TransferenciaModel> findAll() {
        return transferenciaRepository.findAll();
    }
}
