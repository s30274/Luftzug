package pl.edu.pjatk.luftzug.repository;

public interface ICatalogData {
    AircraftRepository getAircrafts();
    AirlineRepository getAirlines();
    AirportRepository getAirports();
    CountryRepository getCountries();
    ScheduleRepository getSchedules();
}
