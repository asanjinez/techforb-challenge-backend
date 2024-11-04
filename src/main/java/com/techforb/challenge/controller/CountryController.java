package com.techforb.challenge.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techforb.challenge.dtos.CountryDto;
import com.techforb.challenge.dtos.CountryIntermediateDto;
import com.techforb.challenge.dtos.response.ApiResponse;
import com.techforb.challenge.exceptions.FlagsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final String uri = "https://restcountries.com/v3.1";
    @GetMapping
    public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri+"/all?fields=name,cca3,flags,translations", String.class);

            // Convertimos el JSON a List<CountryDTO> usando un objeto intermedio
            ObjectMapper objectMapper = new ObjectMapper();
            List<CountryIntermediateDto> countryIntermediateDtos = objectMapper.readValue(result, new TypeReference<List<CountryIntermediateDto>>() {});

            // Convertimos cada CountryIntermediateDTO a CountryDto
            List<CountryDto> countries = countryIntermediateDtos.stream().map(dto -> {
                CountryDto countryDto = new CountryDto();
                countryDto.setCode(dto.getCca3());
                countryDto.setName(dto.getTranslations().getSpa().getCommon());
                countryDto.setImage(dto.getFlags().getSvg());
                return countryDto;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(new ApiResponse<>(true, countries, countries.size() + " países encontrados"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FlagsException("Error al obtener los países");
        }
    }
}