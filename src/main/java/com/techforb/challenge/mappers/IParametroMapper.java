package com.techforb.challenge.mappers;

import com.techforb.challenge.dtos.ParametroDto;
import com.techforb.challenge.dtos.PlantaDto;
import com.techforb.challenge.models.Parametro;
import com.techforb.challenge.models.Planta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IParametroMapper {
    IParametroMapper INSTANCE = Mappers.getMapper(IParametroMapper.class);
    public ParametroDto parametroToParametroDto(Parametro parametro);
    public Parametro parametroDtoToParametro(ParametroDto parametroDto);

}