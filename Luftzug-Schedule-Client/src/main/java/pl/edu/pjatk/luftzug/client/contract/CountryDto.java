package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CountryDto(
        @JsonProperty("CountryCode") String code,
        @JsonProperty("Names") Names names) implements IHaveName {}
