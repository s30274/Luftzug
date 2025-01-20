package pl.edu.pjatk.luftzug.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.repository.AirlineRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IAirlineService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService implements IAirlineService {
    private final AirlineRepository repository;

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
