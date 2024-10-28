package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametroDto {
    @JsonProperty("id")
    private Long idParametros;
    private double temperatura;
    private double presion;
    private double viento;
    private double niveles;
    private double energia;
    private double tension;
    private double monoxido;
    private double gases;
}