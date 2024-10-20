package com.techforb.challenge.dtos;

import lombok.Data;

@Data
public class RegistroDto {
    private String nombre;
    private String apellido;
    private String avatar;

    private String email;
    private String password;

}
