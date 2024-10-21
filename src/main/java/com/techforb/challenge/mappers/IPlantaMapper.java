package com.techforb.challenge.mappers;

import com.techforb.challenge.dtos.PlantaDto;
import com.techforb.challenge.models.Planta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPlantaMapper {
    IPlantaMapper INSTANCE = Mappers.getMapper(IPlantaMapper.class);
     public PlantaDto plantaToPlantaDto(Planta planta);
     public Planta plantaDtoToPlanta(PlantaDto plantaDto);
     public List<PlantaDto> plantasToPlantasDto(List<Planta> plantas);
    public List<Planta> plantasDtoToPlantas(List<PlantaDto> plantasDto);
}
