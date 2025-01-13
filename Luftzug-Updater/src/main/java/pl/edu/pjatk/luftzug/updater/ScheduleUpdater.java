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
        saveAll(departureAirport, arrivalAirport, date);
        System.out.println("Finished updating");
    }

    @Override
    public void updateSchedulesOnly(String departureAirport, String arrivalAirport, LocalDate date){
        saveSchedules(departureAirport, arrivalAirport, date);
        System.out.println("Finished updating");
    }

    private void saveAll(String departureAirport, String arrivalAirport, LocalDate date){
        saveAircrafts();
        saveAirlines();
        saveCountries();
        saveAirports();
        saveSchedules(departureAirport, arrivalAirport, date);
    }

    private void saveAircrafts(){
        try{
            data.getAircrafts().saveAll(mapAllAircrafts());
        } catch(Exception ex){
            System.out.println();
            System.out.println(ex);
        }
    }

    private void saveAirlines(){
        try{
            data.getAirlines().saveAll(mapAllAirlines());
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void saveCountries(){
        try{
            data.getCountries().saveAll(mapAllCountries());
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void saveAirports(){
        try{
            data.getAirports().saveAll(mapAllAirports());
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void saveSchedules(String departureAirport, String arrivalAirport, LocalDate date){
        try{
            data.getSchedules().saveAll(mapAllSchedules(departureAirport, arrivalAirport, date));
        } catch (Exception ex){
            System.out.println("Could not find flight from "+departureAirport+" to "+arrivalAirport+" on "+date);
        }
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
