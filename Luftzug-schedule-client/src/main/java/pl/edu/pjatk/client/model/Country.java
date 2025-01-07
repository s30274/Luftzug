package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Country(
        @JsonProperty("CountryCode") String code,
        @JsonProperty("Names") Names names) {}
