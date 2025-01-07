package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Airport(
        @JsonProperty("AirportCode") String code,
        @JsonProperty("Position") Position position,
        @JsonProperty("Names") Names names,
        @JsonProperty("CountryCode") String countryCode){
    public record Position(@JsonProperty("Coordinate") Coordinate coordinate){
        public record Coordinate(@JsonProperty("Latitude") double latitude,
                                 @JsonProperty("Longitude") double longitude){}
    }
}