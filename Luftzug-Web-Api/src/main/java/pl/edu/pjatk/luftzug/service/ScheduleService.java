package pl.edu.pjatk.luftzug.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ScheduleRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IScheduleService;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService implements IScheduleService {
    private final ScheduleRepository repository;
    public ScheduleService(ScheduleRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Schedule> getScheduleById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Schedule> getScheduleByDepartureAirport(String departureAirportCode) {
        return this.repository.findScheduleByDepartureAirportCode(departureAirportCode);
    }

    @Override
    public List<Schedule> getScheduleByArrivalAirport(String arrivalAirportCode) {
        return this.repository.findScheduleByArrivalAirportCode(arrivalAirportCode);
    }

    @Override
    public List<Schedule> getScheduleByFlightNumber(String flightNumber) {
        return this.repository.findScheduleByFlightNumber(flightNumber);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        this.repository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(Schedule newSchedule, Schedule scheduleToUpdate) {
        scheduleToUpdate.setDuration(newSchedule.getDuration());
        scheduleToUpdate.setDepartureAirport(newSchedule.getDepartureAirport());
        scheduleToUpdate.setDepartureDateTime(newSchedule.getDepartureDateTime());
        scheduleToUpdate.setArrivalAirport(newSchedule.getArrivalAirport());
        scheduleToUpdate.setArrivalDateTime(newSchedule.getArrivalDateTime());
        scheduleToUpdate.setAirline(newSchedule.getAirline());
        scheduleToUpdate.setFlightNumber(newSchedule.getFlightNumber());
        scheduleToUpdate.setAircraft(newSchedule.getAircraft());
        return saveSchedule(scheduleToUpdate);
    }

    @Override
    public void deleteSchedule(Long id) {
        this.repository.deleteById(id);
    }
}
