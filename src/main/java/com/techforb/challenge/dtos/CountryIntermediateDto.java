package com.techforb.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryIntermediateDto {
    private String cca3;
    private Flags flags;
    private Translations translations;

    // Getters y setters
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        private String common;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flags {
        private String svg;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Translations{
        private Spa spa;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Spa {
        private String common;
    }
}
