package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryDto {
    @JsonProperty("id")
    private Long idPais;
    private String code;
    private String name;
    private String image;

}