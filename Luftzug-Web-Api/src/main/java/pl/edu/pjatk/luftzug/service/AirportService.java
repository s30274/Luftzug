package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.IAirportService;

import java.util.List;
import java.util.Optional;

public class AirportService implements IAirportService {
    private final ICatalogData data;

    public AirportService(ICatalogData data){
        this.data = data;
    }

    @Override
    public List<Airport> getAllAirports() {
        return this.data.getAirports().findAll();
    }

    @Override
    public Optional<Airport> getAirportById(Long id) {
        return this.data.getAirports().findById(id);
    }

    @Override
    public Optional<Airport> getAirportByCode(String code) {
        return this.data.getAirports().findAirportByCode(code).stream().findFirst();
    }
}
