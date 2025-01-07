package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Airline(
        @JsonProperty("AirlineID") String code,
        @JsonProperty("Names") Names names) {}
