package com.techforb.challenge.controller;

import com.techforb.challenge.dtos.PlantaDto;
import com.techforb.challenge.dtos.response.ApiResponse;
import com.techforb.challenge.mappers.IPlantaMapper;
import com.techforb.challenge.models.Planta;
import com.techforb.challenge.services.IPlantaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/plantas")
public class PlantaController {
    @Autowired
    private IPlantaService plantaService;

    @Autowired
    private IPlantaMapper plantaMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlantaDto>>> getPlantas() {
        List<Planta> plantas = plantaService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, plantaMapper.plantasToPlantasDto(plantas),plantas.size() + " Plantas encontradas"));


    }
    @GetMapping("/{idPlanta}")
    public ResponseEntity<ApiResponse<PlantaDto>> getPlanta(@PathVariable Long idPlanta) {
        Planta planta = plantaService.findByIdPlanta(idPlanta);
        return ResponseEntity.ok(new ApiResponse<>(true, plantaMapper.plantaToPlantaDto(planta),"Planta con ID: " + idPlanta + " encontrada"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PlantaDto>> createPlanta(@Valid @RequestBody PlantaDto plantaDto) {
        Planta planta = plantaService.createPlanta(plantaDto);
        return ResponseEntity.ok(new ApiResponse<>(true, plantaMapper.plantaToPlantaDto(planta),"Planta con ID: " + planta.getIdPlanta() + " creada exitosamente"));
    }

    @PutMapping("/edit/{idPlanta}")
    public ResponseEntity<ApiResponse<PlantaDto>> updatePlanta(@Valid @RequestBody PlantaDto plantaDto) {
        Planta planta = plantaService.update(plantaDto);
        return ResponseEntity.ok(new ApiResponse<>(true, plantaMapper.plantaToPlantaDto(planta),"Planta con ID: " + planta.getIdPlanta() + " actualizada exitosamente"));
    }

    @DeleteMapping("/delete/{idPlanta}")
    public ResponseEntity<ApiResponse<String>> deletePlanta(@PathVariable Long idPlanta) {
        plantaService.deleteById(idPlanta);
        return ResponseEntity.ok(new ApiResponse<>(true, "Planta con ID: " + idPlanta + " eliminada exitosamente"));
    }
}
