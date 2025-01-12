package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.*;
import pl.edu.pjatk.luftzug.model.*;

public interface ICatalogMappers {
    IMapEntities<AircraftDto, Aircraft> forAircraft();
    IMapEntities<AirlineDto, Airline> forAirline();
    IMapEntities<AirportDto, Airport> forAirport();
    IMapEntities<CountryDto, Country> forCountry();
    IMapEntities<ScheduleDto, Schedule> forSchedule();
}
