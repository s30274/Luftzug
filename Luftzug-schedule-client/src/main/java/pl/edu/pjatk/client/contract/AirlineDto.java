package pl.edu.pjatk.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.client.model.Airline;
import pl.edu.pjatk.client.model.Meta;

import java.util.List;

public record AirlineDto(@JsonProperty("AirlineResource") AirlineResource airlineResource){
    public record AirlineResource(@JsonProperty("Airlines") Airlines airlines,
                                  @JsonProperty("Meta") Meta meta){
        public record Airlines(@JsonProperty("Airline") List<Airline> airlineList){}
    }
}
