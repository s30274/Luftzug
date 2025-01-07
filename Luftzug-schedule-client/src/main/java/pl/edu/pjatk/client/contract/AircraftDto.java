package pl.edu.pjatk.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.client.model.Aircraft;
import pl.edu.pjatk.client.model.Meta;

import java.util.List;

public record AircraftDto(@JsonProperty("AircraftResource") AircraftResource aircraftResource){
    public record AircraftResource(@JsonProperty("AircraftSummaries") Aircrafts aircrafts,
                                   @JsonProperty("Meta") Meta meta){
        public record Aircrafts(@JsonProperty("AircraftSummary")List<Aircraft> aircraftList){}
    }
}
