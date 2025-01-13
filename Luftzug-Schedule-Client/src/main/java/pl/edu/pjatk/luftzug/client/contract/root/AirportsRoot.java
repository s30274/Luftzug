package pl.edu.pjatk.luftzug.client.contract.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.luftzug.client.contract.AirportDto;
import pl.edu.pjatk.luftzug.client.contract.Meta;

import java.util.List;

public record AirportsRoot(@JsonProperty("AirportResource") AirportResource airportResource) implements IRoot{
    public record AirportResource(@JsonProperty("Airports") Airports airports,
                                  @JsonProperty("Meta") Meta meta) {
        public record Airports(@JsonProperty("Airport") List<AirportDto> airportDtos){}
    }
}
