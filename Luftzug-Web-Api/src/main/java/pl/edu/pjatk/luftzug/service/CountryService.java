package pl.edu.pjatk.luftzug.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.CountryRepository;
import pl.edu.pjatk.luftzug.service.abstraction.ICountryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {
    private final CountryRepository repository;

    @Override
    public List<Country> getAllCountries() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Country> getCountryByCode(String code){
        return this.repository.findCountryByCode(code).stream().findFirst();
    }
}
