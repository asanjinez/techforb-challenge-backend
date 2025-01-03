package com.techforb.challenge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "plantas")
public class Planta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanta;
    private String nombre;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais", nullable = false)
    private Country pais;
    private Integer numeroLecturas;
    private Integer numeroAlertasMedias;
    private Integer numeroAlertasRojas;
    private Integer sensoresDeshabilitados;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_parametro", referencedColumnName = "idParametro")
    private Parametro parametros;

}
