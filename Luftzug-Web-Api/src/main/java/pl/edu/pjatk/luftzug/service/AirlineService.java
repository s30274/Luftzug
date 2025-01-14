package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.IAirlineService;

import java.util.List;
import java.util.Optional;

public class AirlineService implements IAirlineService {
    private final ICatalogData data;

    public AirlineService(ICatalogData data){
        this.data = data;
    }

    @Override
    public List<Airline> getAllAirlines() {
        return this.data.getAirlines().findAll();
    }

    @Override
    public Optional<Airline> getAirlineById(Long id) {
        return this.data.getAirlines().findById(id);
    }

    @Override
    public Optional<Airline> getAirlineByCode(String code){
        return this.data.getAirlines().findAirlineByCode(code).stream().findFirst();
    }
}
