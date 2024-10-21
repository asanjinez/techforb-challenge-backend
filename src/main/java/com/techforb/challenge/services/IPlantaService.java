package com.techforb.challenge.services;


import com.techforb.challenge.dtos.PlantaDto;
import com.techforb.challenge.models.Planta;

import java.util.List;

public interface IPlantaService {
    public List<Planta> findAll();
    public Planta findByIdPlanta(Long idPlanta);
    public Planta createPlanta(PlantaDto plantaDto);
    public void deleteById(Long idPlanta);
    public Planta update(PlantaDto plantaDto);
}
