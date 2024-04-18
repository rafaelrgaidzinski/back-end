package com.example.oscar.services;

import com.example.oscar.models.ActorModel;
import com.example.oscar.repositories.ActorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Transactional
    public ActorModel save(ActorModel actorModel) {
        return actorRepository.save(actorModel);
    }

    @Transactional
    public void delete(ActorModel actorModel) {
        actorRepository.delete(actorModel);
    }

    public Optional<ActorModel> findById(UUID id) {
        return actorRepository.findById(id);
    }

    public List<ActorModel> findAll() {
        return actorRepository.findAll();
    }

}
