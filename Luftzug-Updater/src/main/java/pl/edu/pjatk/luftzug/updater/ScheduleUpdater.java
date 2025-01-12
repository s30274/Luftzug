package pl.edu.pjatk.luftzug.updater;

import pl.edu.pjatk.luftzug.client.IScheduleClient;
import pl.edu.pjatk.luftzug.mapper.ICatalogMappers;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

import java.time.LocalDate;
import java.util.List;

public class ScheduleUpdater implements IUpdateSchedules{
    private final ICatalogData data;
    private final IScheduleClient client;
    private final ICatalogMappers entityMapper;

    public ScheduleUpdater(IScheduleClient scheduleClient, ICatalogData data, ICatalogMappers entityMapper){
        this.client = scheduleClient;
        this.data = data;
        this.entityMapper = entityMapper;
    }

    @Override
    public void updateAll(String departureAirport, String arrivalAirport, LocalDate date) {
        data.getAircrafts().saveAll(mapAllAircrafts());
        data.getAirlines().saveAll(mapAllAirlines());
        data.getCountries().saveAll(mapAllCountries());
        data.getAirports().saveAll(mapAllAirports());
        data.getSchedules().saveAll(mapAllSchedules(departureAirport, arrivalAirport, date));
    }

    @Override
    public void updateSchedulesOnly(String departureAirport, String arrivalAirport, LocalDate date){
        data.getSchedules().saveAll(mapAllSchedules(departureAirport, arrivalAirport, date));
    }

    private List<Aircraft> mapAllAircrafts(){
        return client.getAllAircrafts()
                .stream()
                .map(aircraftDto -> entityMapper.forAircraft().map(aircraftDto))
                .toList();
    }

    private List<Airline> mapAllAirlines(){
        return client.getAllAirlines()
                .stream()
                .map(airlineDto -> entityMapper.forAirline().map(airlineDto))
                .toList();
    }

    private List<Airport> mapAllAirports(){
        return client.getAllAirports()
                .stream()
                .map(airportDto -> entityMapper.forAirport().map(airportDto))
                .toList();
    }

    private List<Country> mapAllCountries(){
        return client.getAllCountries()
                .stream()
                .map(countryDto -> entityMapper.forCountry().map(countryDto))
                .toList();
    }

    private List<Schedule> mapAllSchedules(String departureAirport, String arrivalAirport, LocalDate date){
        return client.getAllSchedules(departureAirport, arrivalAirport, date)
                .stream()
                .map(scheduleDto -> entityMapper.forSchedule().map(scheduleDto))
                .toList();
    }
}
