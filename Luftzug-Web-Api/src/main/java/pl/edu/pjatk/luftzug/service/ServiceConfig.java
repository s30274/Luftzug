package pl.edu.pjatk.luftzug.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.*;

@Configuration
public class ServiceConfig {
    @Bean
    public IAircraftService aircraftService(ICatalogData data){
        return new AircraftService(data.getAircrafts());
    }

    @Bean
    public IAirlineService airlineService(ICatalogData data){
        return new AirlineService(data.getAirlines());
    }

    @Bean
    public IAirportService airportService(ICatalogData data){
        return new AirportService(data.getAirports());
    }

    @Bean
    public ICountryService countryService(ICatalogData data){
        return new CountryService(data.getCountries());
    }
}
