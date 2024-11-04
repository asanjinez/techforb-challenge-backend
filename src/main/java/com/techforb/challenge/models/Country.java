package com.techforb.challenge.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPais;
    private String code;
    private String name;
    private String image;

}
