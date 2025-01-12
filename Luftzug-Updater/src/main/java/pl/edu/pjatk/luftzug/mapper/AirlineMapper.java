package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.AirlineDto;
import pl.edu.pjatk.luftzug.model.Airline;

public class AirlineMapper implements IMapEntities<AirlineDto, Airline> {
    @Override
    public Airline map(AirlineDto airlineDto) {
        return map(airlineDto, new Airline());
    }

    @Override
    public Airline map(AirlineDto airlineDto, Airline airline) {
        airline.setCode(airlineDto.code());
        airline.setName(extractName(airlineDto));
        return airline;
    }
}
