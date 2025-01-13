package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AirportDto(
        @JsonProperty("AirportCode") String code,
        @JsonProperty("Position") Position position,
        @JsonProperty("Names") Names names,
        @JsonProperty("CountryCode") String countryCode) implements IHaveName {
    public record Position(@JsonProperty("Coordinate") Coordinate coordinate){
        public record Coordinate(@JsonProperty("Latitude") double latitude,
                                 @JsonProperty("Longitude") double longitude){}
    }
}