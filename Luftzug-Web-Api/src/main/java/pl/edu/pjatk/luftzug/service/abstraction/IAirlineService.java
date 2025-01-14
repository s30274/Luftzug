package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.model.Airline;

import java.util.List;
import java.util.Optional;

public interface IAirlineService{
    List<Airline> getAllAirlines();

    Optional<Airline> getAirlineById(Long id);

    Optional<Airline> getAirlineByCode(String code);
}
