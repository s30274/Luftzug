package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AircraftDto(
        @JsonProperty("AircraftCode") String code,
        @JsonProperty("Names") Names names
) implements IHaveName {}
