package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.model.*;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    List<Schedule> getAllSchedules();

    Optional<Schedule> getScheduleById(Long id);

    List<Schedule> getScheduleByDepartureAirport(String departureAirportCode);

    List<Schedule> getScheduleByArrivalAirport(String arrivalAirportCode);

    List<Schedule> getScheduleByFlightNumber(String flightNumber);

    void saveSchedule(Schedule schedule);

    void updateSchedule(Schedule newSchedule, Schedule scheduleToUpdate);

    void deleteSchedule(Long id);
}
