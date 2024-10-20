package com.techforb.challenge.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "credenciales")
public class Credencial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredencial;
    private String email;
    private String passwordHash;
    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
}
