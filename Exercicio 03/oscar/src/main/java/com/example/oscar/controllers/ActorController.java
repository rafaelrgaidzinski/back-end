package com.example.oscar.controllers;

import com.example.oscar.dtos.ActorRecordDto;
import com.example.oscar.models.ActorModel;
import com.example.oscar.services.ActorService;
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
@RequestMapping("/actors")
@Scope("prototype")
public class ActorController {

    @Autowired
    ActorService actorService;

    @PostMapping
    public ResponseEntity<ActorModel> saveActor(@RequestBody @Valid ActorRecordDto actorRecordDto){
        var actorModel = new ActorModel();
        BeanUtils.copyProperties(actorRecordDto, actorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(actorModel));
    }

    @GetMapping
    public ResponseEntity<List<ActorModel>> getAllActors() {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneActor(@PathVariable(value = "id") UUID id) {
        Optional<ActorModel> actorModelOptional = actorService.findById(id);
        if(actorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(actorModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateActor(@PathVariable(value = "id") UUID id) {
        Optional<ActorModel> actorModelOptional = actorService.findById(id);
        if(actorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found.");
        }
        var actorModel = new ActorModel();
        BeanUtils.copyProperties(actorModelOptional.get(), actorModel);
        actorModel.setId(actorModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(actorService.save(actorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActor(@PathVariable(value = "id") UUID id) {
        Optional<ActorModel> actorModelOptional = actorService.findById(id);
        if(actorModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found.");
        }
        actorService.delete(actorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Actor deleted successfully.");
    }

}
