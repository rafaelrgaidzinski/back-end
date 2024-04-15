package com.example.oscar.controllers;

import com.example.oscar.dtos.IndicationRecordDto;
import com.example.oscar.models.IndicationModel;
import com.example.oscar.services.IndicationService;
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
@RequestMapping("/indications")
@Scope("prototype")
public class IndicationController {

    @Autowired
    IndicationService indicationService;

    @PostMapping
    public ResponseEntity<IndicationModel> saveIndication(@RequestBody @Valid IndicationRecordDto indicationRecordDto){
        var indicationModel = new IndicationModel();
        BeanUtils.copyProperties(indicationRecordDto, indicationModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(indicationService.save(indicationModel));
    }

    @GetMapping
    public ResponseEntity<List<IndicationModel>> getAllIndications() {
        return ResponseEntity.status(HttpStatus.OK).body(indicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneIndication(@PathVariable(value = "id") UUID id) {
        Optional<IndicationModel> indicationModelOptional = indicationService.findById(id);
        if(indicationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indication not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(indicationModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateIndication(@PathVariable(value = "id") UUID id) {
        Optional<IndicationModel> indicationModelOptional = indicationService.findById(id);
        if(indicationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indication not found.");
        }
        var indicationModel = new IndicationModel();
        BeanUtils.copyProperties(indicationModelOptional.get(), indicationModel);
        indicationModel.setId(indicationModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(indicationService.save(indicationModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIndication(@PathVariable(value = "id") UUID id) {
        Optional<IndicationModel> indicationModelOptional = indicationService.findById(id);
        if(indicationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Indication not found.");
        }
        indicationService.delete(indicationModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Indication deleted successfully.");
    }

}
