package pl.edu.pjatk.luftzug.client.contract.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.luftzug.client.contract.AirlineDto;
import pl.edu.pjatk.luftzug.client.contract.Meta;

import java.util.List;

public record AirlinesRoot(@JsonProperty("AirlineResource") AirlineResource airlineResource) implements IRoot{
    public record AirlineResource(@JsonProperty("Airlines") Airlines airlines,
                                  @JsonProperty("Meta") Meta meta) {
        public record Airlines(@JsonProperty("Airline") List<AirlineDto> airlineDtos){}
    }
}
