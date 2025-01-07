package pl.edu.pjatk.client;

import pl.edu.pjatk.client.contract.*;
import pl.edu.pjatk.client.model.*;

import java.time.LocalDate;
import java.util.List;

public interface IScheduleClient {
    List<Aircraft> getAllAircrafts();
    List<Airline> getAllAirlines();
    List<Airport> getAllAirports();
    List<Country> getAllCountries();
    List<Schedule> getAllSchedules(String departureAirport, String arrivalAirport, LocalDate date);
}
