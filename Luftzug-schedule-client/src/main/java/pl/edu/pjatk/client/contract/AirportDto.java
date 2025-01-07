package pl.edu.pjatk.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.client.model.Airport;
import pl.edu.pjatk.client.model.Meta;

import java.util.List;

public record AirportDto(@JsonProperty("AirportResource") AirportResource airportResource){
    public record AirportResource(@JsonProperty("Airports") Airports airports,
                                  @JsonProperty("Meta") Meta meta){
        public record Airports(@JsonProperty("Airport") List<Airport> airportList){}
    }
}
