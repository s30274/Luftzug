package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.model.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    List<Country> getAllCountries();

    Optional<Country> getCountryById(Integer id);

    Optional<Country> getCountryByCode(String code);
}
