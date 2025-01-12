package pl.edu.pjatk.luftzug.updater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.luftzug.client.ScheduleClient;
import pl.edu.pjatk.luftzug.mapper.*;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

@Configuration
public class ScheduleUpdaterConfig {

    @Bean
    public AircraftMapper aircraftMapper(){
        return new AircraftMapper();
    }

    @Bean
    public AirlineMapper airlineMapper(){
        return new AirlineMapper();
    }

    @Bean
    public AirportMapper airportMapper(ICatalogData catalogData){
        return new AirportMapper(catalogData);
    }

    @Bean
    public CountryMapper countryMapper(){
        return new CountryMapper();
    }

    @Bean
    public ScheduleMapper scheduleMapper(ICatalogData catalogData){
        return new ScheduleMapper(catalogData);
    }

    @Bean
    public ICatalogMappers entityMapper(AircraftMapper aircraftMapper, AirlineMapper airlineMapper, AirportMapper airportMapper, CountryMapper countryMapper, ScheduleMapper scheduleMapper){
        return new EntityMapper(aircraftMapper, airlineMapper, airportMapper, countryMapper, scheduleMapper);
    }

    @Bean
    public IUpdateSchedules updater(ScheduleClient scheduleClient, ICatalogData catalogData, ICatalogMappers entityMapper){
        return new ScheduleUpdater(scheduleClient, catalogData, entityMapper);
    }
}
