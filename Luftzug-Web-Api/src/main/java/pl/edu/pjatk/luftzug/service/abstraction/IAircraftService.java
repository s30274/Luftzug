package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.model.Aircraft;

import java.util.List;
import java.util.Optional;

public interface IAircraftService {
    List<Aircraft> getAllAircrafts();

    Optional<Aircraft> getAircraftById(Long id);

    Optional<Aircraft> getAircraftByCode(String code);
}
