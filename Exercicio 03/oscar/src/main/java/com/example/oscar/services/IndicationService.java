package com.example.oscar.services;

import com.example.oscar.models.IndicationModel;
import com.example.oscar.repositories.IndicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndicationService {

    @Autowired
    IndicationRepository indicationRepository;

    @Transactional
    public IndicationModel save(IndicationModel indicationModel) {
        return indicationRepository.save(indicationModel);
    }

    @Transactional
    public void delete(IndicationModel indicationModel) {
        indicationRepository.delete(indicationModel);
    }

    public Optional<IndicationModel> findById(UUID id) {
        return indicationRepository.findById(id);
    }

    public List<IndicationModel> findAll() {
        return indicationRepository.findAll();
    }

}
