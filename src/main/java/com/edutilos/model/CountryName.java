package com.edutilos.model;

import lombok.Data;

@Data
public class CountryName {
    private String country;
    private String name;

    public CountryName(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public CountryName() {
    }
}
