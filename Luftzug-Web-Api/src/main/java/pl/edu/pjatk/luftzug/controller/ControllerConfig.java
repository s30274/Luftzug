package pl.edu.pjatk.luftzug.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.*;
import pl.edu.pjatk.luftzug.service.abstraction.*;

@Configuration
public class ControllerConfig {

    @Bean
    public IAircraftService aircraftService(ICatalogData data){
        return new AircraftService(data);
    }

    @Bean
    public IAirlineService airlineService(ICatalogData data){
        return new AirlineService(data);
    }

    @Bean
    public IAirportService airportService(ICatalogData data){
        return new AirportService(data);
    }

    @Bean
    public ICountryService countryService(ICatalogData data){
        return new CountryService(data);
    }
}
