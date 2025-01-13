package pl.edu.pjatk.luftzug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByDepartureAirportCode(String departureAirportCode);
    List<Schedule> findScheduleByArrivalAirportCode(String arrivalAirportCode);
    List<Schedule> findScheduleByFlightNumber(String flightNumber);
}
