package pl.edu.pjatk.luftzug.client;

import pl.edu.pjatk.luftzug.client.contract.*;

import java.time.LocalDate;
import java.util.List;

public interface IScheduleClient {
    List<AircraftDto> getAllAircrafts();
    List<AirlineDto> getAllAirlines();
    List<AirportDto> getAllAirports();
    List<CountryDto> getAllCountries();
    List<ScheduleDto> getAllSchedules(String departureAirport, String arrivalAirport, LocalDate date);
}
