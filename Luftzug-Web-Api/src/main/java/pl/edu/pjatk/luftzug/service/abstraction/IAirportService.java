package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.model.Airport;

import java.util.List;
import java.util.Optional;

public interface IAirportService {
    List<Airport> getAllAirports();

    Optional<Airport> getAirportById(Long id);

    Optional<Airport> getAirportByCode(String code);
}
