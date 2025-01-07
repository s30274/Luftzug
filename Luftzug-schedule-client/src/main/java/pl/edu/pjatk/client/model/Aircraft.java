package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Aircraft(
        @JsonProperty("AircraftCode") String code,
        @JsonProperty("Names") Names names
) {}
