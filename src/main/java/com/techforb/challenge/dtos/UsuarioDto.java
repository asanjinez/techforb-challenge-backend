package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioDto {
    @JsonProperty("id ")
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String avatar;
}


