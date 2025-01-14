package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.repository.AirportRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IAirportService;

import java.util.List;
import java.util.Optional;

public class AirportService implements IAirportService {
    private final AirportRepository repository;

    public AirportService(AirportRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Airport> getAllAirports() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Airport> getAirportById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Airport> getAirportByCode(String code) {
        return this.repository.findAirportByCode(code).stream().findFirst();
    }
}
