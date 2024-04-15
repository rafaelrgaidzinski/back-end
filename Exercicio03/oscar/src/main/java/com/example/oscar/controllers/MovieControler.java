package com.example.oscar.controllers;

import com.example.oscar.dtos.MovieRecordDto;
import com.example.oscar.models.MovieModel;
import com.example.oscar.services.MovieService;
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
@RequestMapping("/movies")
@Scope("prototype")
public class MovieControler {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieModel> saveMovie(@RequestBody @Valid MovieRecordDto movieRecordDto){
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieRecordDto, movieModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieModel));
    }

    @GetMapping
    public ResponseEntity<List<MovieModel>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMovie(@PathVariable(value = "id") UUID id) {
        Optional<MovieModel> movieModelOptional = movieService.findById(id);
        if(movieModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable(value = "id") UUID id) {
        Optional<MovieModel> movieModelOptional = movieService.findById(id);
        if(movieModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieModelOptional.get(), movieModel);
        movieModel.setId(movieModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActor(@PathVariable(value = "id") UUID id) {
        Optional<MovieModel> movieModelOptional = movieService.findById(id);
        if(movieModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }
        movieService.delete(movieModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully.");
    }

}
