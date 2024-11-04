package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PlantaDto {
    public interface Crear{}
    public interface Actualizar{}

    @JsonProperty("id")
    @NotNull(groups = {Actualizar.class}, message = "el id de la planta es obligatorio")
    @NotBlank(groups = {Actualizar.class}, message = "el id de la planta no puede estar vacio")
    private Long idPlanta;

    @NotBlank(groups = {Crear.class, Actualizar.class}, message = "el nombre es obligatorio")
    @NotNull(groups = {Crear.class, Actualizar.class}, message = "el nombre no puede estar vacio")
    private String nombre;

    @NotBlank(groups = {Actualizar.class}, message = "el pais es obligatorio")
    @NotNull(groups = {Actualizar.class}, message = "el pais no puede estar vacio")
    private String pais;

    @NotBlank(groups = {Actualizar.class}, message = "el numero de lecturas es obligatorio")
    @NotNull(groups = {Actualizar.class}, message = "el numero de lecturas no puede estar vacio")
    private Integer numeroLecturas;

    @NotBlank(groups = {Actualizar.class}, message = "el numero de aletas medias es obligatorio")
    @NotNull(groups = {Actualizar.class}, message = "el numero de alertas medias no puede estar vacio")
    private Integer numeroAlertasMedias;

    @NotBlank(groups = {Actualizar.class}, message = "el numero de alertas rojas es obligatorio")
    @NotNull(groups = {Actualizar.class}, message = "el numero de alertas rojas no puede estar vacio")
    private Integer numeroAlertasRojas;

    @NotBlank(groups = {Actualizar.class}, message = "el numero de sensores deshabilitados es obligatorio")
    @NotNull(groups = {Actualizar.class}, message = "el numero de sensores deshabdilitados no puede estar vacio")
    private Integer sensoresDeshabilitados;

    @NotBlank(groups = {Actualizar.class}, message = "el parametro es ogatorio")
    @NotNull(groups = {Actualizar.class}, message = "el parameto no puede estar vacio")
    private ParametroDto parametros;

}
