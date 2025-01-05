package pl.edu.pjatk.repository;

import pl.edu.pjatk.model.Aircraft;
import pl.edu.pjatk.model.Airline;
import pl.edu.pjatk.model.Airport;

public interface ICatalogData {
    AircraftRepository getAircrafts();
    AirlineRepository getAirlines();
    AirportRepository getAirports();
    CountryRepository getCountries();
    ScheduleRepository getSchedules();
}
