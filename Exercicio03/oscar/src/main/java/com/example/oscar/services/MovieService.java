package com.example.oscar.services;

import com.example.oscar.models.MovieModel;
import com.example.oscar.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    public MovieModel save(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    @Transactional
    public void delete(MovieModel movieModel) {
        movieRepository.delete(movieModel);
    }

    public Optional<MovieModel> findById(UUID id) {
        return movieRepository.findById(id);
    }

    public List<MovieModel> findAll() {
        return movieRepository.findAll();
    }

}
