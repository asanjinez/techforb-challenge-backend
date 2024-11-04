package com.techforb.challenge.services;

import com.techforb.challenge.dtos.PlantaDto;
import com.techforb.challenge.exceptions.ResourceAlreadyExistsException;
import com.techforb.challenge.exceptions.ResourceNotFoundException;
import com.techforb.challenge.mappers.IPlantaMapper;
import com.techforb.challenge.models.Planta;
import com.techforb.challenge.respostories.IPlantasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service @Slf4j
public class PlantaServiceImpl implements IPlantaService {
    @Autowired
    private IPlantaMapper plantaMapper;
    @Autowired
    private IPlantasRepository plantasRepository;

    @Override
    public Planta createPlanta(PlantaDto plantaDto) {
        if (plantaDto.getIdPlanta() != null  && plantasRepository.existsById(plantaDto.getIdPlanta())) {
            throw new ResourceAlreadyExistsException("Ya existe una planta con el ID: " + plantaDto.getIdPlanta());
        }
        if (plantasRepository.existsByNombreAndPais_Name(plantaDto.getNombre(), plantaDto.getPais().getName())) {
            throw new ResourceAlreadyExistsException("Ya existe una planta con el nombre: " + plantaDto.getNombre());
        }
        return plantasRepository.save(plantaMapper.plantaDtoToPlanta(plantaDto));
    }
    @Override
    public List<Planta> findAll() {
        return plantasRepository.findAll();
    }
    @Override
    public Planta findByIdPlanta(Long idPlanta) {
        return getPlantaById(idPlanta);

    }
    @Override
    public void deleteById(Long idPlanta) {
        Planta planta = getPlantaById(idPlanta);
        plantasRepository.delete(planta);
    }
    @Override
    public Planta update(PlantaDto plantaDto) {
        Planta plantaToedit = getPlantaById(plantaDto.getIdPlanta());
//        En la sistema aparentemente no nos permite editar nombre y pais, pero si lo hiciera deberiamos verificar que no exista una planta con el mismo nombre y pais
//        if (plantasRepository.existsByNombreAndPais(plantaDto.getNombre(), plantaDto.getPais())) {
//            throw new ResourceAlreadyExistsException("Ya existe una planta con el nombre: " + plantaDto.getNombre());
//        }

        plantaToedit.setNombre(plantaDto.getNombre());
        plantaToedit.setPais(plantaDto.getPais());
        plantaToedit.setNumeroLecturas(plantaDto.getNumeroLecturas());
        plantaToedit.setNumeroAlertasMedias(plantaDto.getNumeroAlertasMedias());
        plantaToedit.setNumeroAlertasRojas(plantaDto.getNumeroAlertasRojas());
        plantaToedit.setSensoresDeshabilitados(plantaDto.getSensoresDeshabilitados());
        return plantasRepository.save(plantaToedit);
    }

    private Planta getPlantaById(Long idPlanta) {
        Optional<Planta> planta = plantasRepository.findByIdPlanta(idPlanta);
        if (planta.isEmpty())
            throw new ResourceNotFoundException("Planta no encontrada con el id: " + idPlanta);
        return planta.get();
    }
}
