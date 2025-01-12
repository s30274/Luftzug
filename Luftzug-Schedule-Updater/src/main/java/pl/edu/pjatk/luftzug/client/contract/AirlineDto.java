package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AirlineDto(
        @JsonProperty("AirlineID") String code,
        @JsonProperty("Names") Names names
) implements IHaveName {}
