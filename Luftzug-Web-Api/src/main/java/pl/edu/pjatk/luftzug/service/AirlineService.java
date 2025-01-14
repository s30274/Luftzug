package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.repository.AirlineRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IAirlineService;

import java.util.List;
import java.util.Optional;

public class AirlineService implements IAirlineService {
    private final AirlineRepository repository;

    public AirlineService(AirlineRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Airline> getAllAirlines() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Airline> getAirlineById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Airline> getAirlineByCode(String code){
        return this.repository.findAirlineByCode(code).stream().findFirst();
    }
}
