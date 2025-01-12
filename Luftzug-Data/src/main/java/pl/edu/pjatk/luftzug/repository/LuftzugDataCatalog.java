package pl.edu.pjatk.luftzug.repository;

import org.springframework.stereotype.Repository;

@Repository
public class LuftzugDataCatalog implements ICatalogData {
    private final AircraftRepository aircrafts;
    private final AirlineRepository airlines;
    private final AirportRepository airports;
    private final CountryRepository countries;
    private final ScheduleRepository schedules;

    public LuftzugDataCatalog(AircraftRepository aircrafts, AirlineRepository airlines, AirportRepository airports, CountryRepository countries, ScheduleRepository schedules){
        this.aircrafts = aircrafts;
        this.airlines = airlines;
        this.airports = airports;
        this.countries = countries;
        this.schedules = schedules;
    }

    public AircraftRepository getAircrafts(){
        return aircrafts;
    }

    public AirlineRepository getAirlines(){
        return airlines;
    }

    public AirportRepository getAirports(){
        return airports;
    }

    public CountryRepository getCountries(){
        return countries;
    }

    public ScheduleRepository getSchedules(){
        return schedules;
    }
}
