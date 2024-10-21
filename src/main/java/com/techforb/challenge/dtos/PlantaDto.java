package com.techforb.challenge.dtos;

import lombok.Data;

@Data
public class PlantaDto {
    private Long idPlanta;
    private String nombre;
    private String pais;
    private String numeroLecturas;
    private String numeroAlertasMedias;
    private String numeroAlertasRojas;
    private String sensoresDeshabilitados;
}
