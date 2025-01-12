package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.*;
import pl.edu.pjatk.luftzug.model.*;

public class EntityMapper implements ICatalogMappers {

    private final IMapEntities<AircraftDto, Aircraft> forAircraft;
    private final IMapEntities<AirlineDto, Airline> forAirline;
    private final IMapEntities<AirportDto, Airport> forAirport;
    private final IMapEntities<CountryDto, Country> forCountry;
    private final IMapEntities<ScheduleDto, Schedule> forSchedule;

    public EntityMapper(IMapEntities<AircraftDto, Aircraft> forAircraft, IMapEntities<AirlineDto, Airline> forAirline, IMapEntities<AirportDto, Airport> forAirport, IMapEntities<CountryDto, Country> forCountry, IMapEntities<ScheduleDto, Schedule> forSchedule) {
        this.forAircraft = forAircraft;
        this.forAirline = forAirline;
        this.forAirport = forAirport;
        this.forCountry = forCountry;
        this.forSchedule = forSchedule;
    }

    @Override
    public IMapEntities<AircraftDto, Aircraft> forAircraft() {
        return forAircraft;
    }

    @Override
    public IMapEntities<AirlineDto, Airline> forAirline() {
        return forAirline;
    }

    @Override
    public IMapEntities<AirportDto, Airport> forAirport() {
        return forAirport;
    }

    @Override
    public IMapEntities<CountryDto, Country> forCountry() {
        return forCountry;
    }

    @Override
    public IMapEntities<ScheduleDto, Schedule> forSchedule() {
        return forSchedule;
    }
}
