package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    @JsonProperty("usuario")
    private UsuarioDto usuarioDto;

    public AuthResponseDto(String token, UsuarioDto usuarioDto) {
        this.token = token;
        this.usuarioDto = usuarioDto;
    }
}
