package pl.edu.pjatk.luftzug.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.IScheduleService;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService implements IScheduleService {
    private final ICatalogData data;
    public ScheduleService(ICatalogData data){
        this.data = data;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return this.data.getSchedules().findAll();
    }

    @Override
    public Optional<Schedule> getScheduleById(Long id) {
        return this.data.getSchedules().findById(id);
    }

    @Override
    public List<Schedule> getScheduleByDepartureAirport(String departureAirportCode) {
        return this.data.getSchedules().findScheduleByDepartureAirportCode(departureAirportCode);
    }

    @Override
    public List<Schedule> getScheduleByArrivalAirport(String arrivalAirportCode) {
        return this.data.getSchedules().findScheduleByArrivalAirportCode(arrivalAirportCode);
    }

    @Override
    public List<Schedule> getScheduleByFlightNumber(String flightNumber) {
        return this.data.getSchedules().findScheduleByFlightNumber(flightNumber);
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        this.data.getSchedules().save(schedule);
    }

    @Override
    public void updateSchedule(Schedule newSchedule, Schedule scheduleToUpdate) {
        scheduleToUpdate.setDuration(newSchedule.getDuration());
        scheduleToUpdate.setDepartureAirport(newSchedule.getDepartureAirport());
        scheduleToUpdate.setDepartureDateTime(newSchedule.getDepartureDateTime());
        scheduleToUpdate.setArrivalAirport(newSchedule.getArrivalAirport());
        scheduleToUpdate.setArrivalDateTime(newSchedule.getArrivalDateTime());
        scheduleToUpdate.setAirline(newSchedule.getAirline());
        scheduleToUpdate.setFlightNumber(newSchedule.getFlightNumber());
        scheduleToUpdate.setAircraft(newSchedule.getAircraft());
        saveSchedule(scheduleToUpdate);
    }

    @Override
    public void deleteSchedule(Long id) {
        this.data.getSchedules().deleteById(id);
    }
}
