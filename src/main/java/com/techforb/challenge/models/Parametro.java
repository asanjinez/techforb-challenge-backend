package com.techforb.challenge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "parametros")
public class Parametro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParametro;
    private Integer temperatura;
    private Integer presion;
    private Integer viento;
    private Integer niveles;
    private Integer energia;
    private Integer tension;
    private Integer monoxido;
    private Integer gases;

}