package com.example.oscar.services;

import com.example.oscar.models.FilmeModel;
import com.example.oscar.repositories.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    @Transactional
    public FilmeModel save(FilmeModel filmeModel) {
        return filmeRepository.save(filmeModel);
    }

    @Transactional
    public void delete(FilmeModel filmeModel) {
        filmeRepository.delete(filmeModel);
    }

    public Optional<FilmeModel> findById(UUID id) {
        return filmeRepository.findById(id);
    }

    public List<FilmeModel> findAll() {
        return filmeRepository.findAll();
    }

}
