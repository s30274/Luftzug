package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.IAircraftService;

import java.util.List;
import java.util.Optional;

public class AircraftService implements IAircraftService {
    private final ICatalogData data;

    public AircraftService(ICatalogData data){
        this.data = data;
    }

    @Override
    public List<Aircraft> getAllAircrafts() {
        return this.data.getAircrafts().findAll();
    }

    @Override
    public Optional<Aircraft> getAircraftById(Long id) {
        return this.data.getAircrafts().findById(id);
    }

    @Override
    public Optional<Aircraft> getAircraftByCode(String code) {
        return this.data.getAircrafts().findAircraftByCode(code).stream().findFirst();
    }
}
