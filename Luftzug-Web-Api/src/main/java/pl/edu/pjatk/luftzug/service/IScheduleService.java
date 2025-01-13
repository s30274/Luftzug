package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    void saveSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    Optional<Schedule> getScheduleById(Long id);

    List<Schedule> getScheduleByDepartureAirport(String departureAirportCode);

    List<Schedule> getScheduleByArrivalAirport(String arrivalAirportCode);

    List<Schedule> getScheduleByFlightNumber(String flightNumber);
}
