package pl.edu.pjatk.luftzug.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.repository.AircraftRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IAircraftService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftService implements IAircraftService {
    private final AircraftRepository repository;

    @Override
    public List<Aircraft> getAllAircrafts() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Aircraft> getAircraftById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Aircraft> getAircraftByCode(String code) {
        return this.repository.findAircraftByCode(code).stream().findFirst();
    }
}
