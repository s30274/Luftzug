package pl.edu.pjatk.luftzug.client.contract.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.pjatk.luftzug.client.contract.Meta;
import pl.edu.pjatk.luftzug.client.contract.AircraftDto;

import java.util.List;

public record AircraftRoot(@JsonProperty("AircraftResource") AircraftResource aircraftResource) implements IRoot{
    public record AircraftResource(@JsonProperty("AircraftSummaries") Aircrafts aircrafts,
                                   @JsonProperty("Meta") Meta meta) {
        public record Aircrafts(@JsonProperty("AircraftSummary")List<AircraftDto> aircraftDtos){}
    }
}
