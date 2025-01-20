package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.*;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    List<ScheduleDto> getAllSchedules();

    ScheduleDto getScheduleById(Long id);

    List<ScheduleDto> getScheduleByDepartureAirport(String departureAirportCode);

    List<ScheduleDto> getScheduleByArrivalAirport(String arrivalAirportCode);

    ScheduleDto getScheduleByFlightNumber(String flightNumber);

    Schedule saveSchedule(ScheduleDto dto);

    Schedule updateSchedule(ScheduleDto scheduleToUpdate);

    void deleteSchedule(Long id);
}
