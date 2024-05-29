package com.example.oscar.services;

import com.example.oscar.models.AtorModel;
import com.example.oscar.repositories.AtorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtorService {

    @Autowired
    AtorRepository atorRepository;

    @Transactional
    public AtorModel save(AtorModel atorModel) {
        return atorRepository.save(atorModel);
    }

    @Transactional
    public void delete(AtorModel atorModel) {
        atorRepository.delete(atorModel);
    }

    public Optional<AtorModel> findById(UUID id) {
        return atorRepository.findById(id);
    }

    public List<AtorModel> findAll() {
        return atorRepository.findAll();
    }

}
