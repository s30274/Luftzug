package pl.edu.pjatk.luftzug.service;

import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.ICatalogData;
import pl.edu.pjatk.luftzug.service.abstraction.ICountryService;

import java.util.List;
import java.util.Optional;

public class CountryService implements ICountryService {
    private final ICatalogData data;

    public CountryService(ICatalogData data){
        this.data = data;
    }

    @Override
    public List<Country> getAllCountries() {
        return this.data.getCountries().findAll();
    }

    @Override
    public Optional<Country> getCountryById(Integer id) {
        return this.data.getCountries().findById(id);
    }

    @Override
    public Optional<Country> getCountryByCode(String code){
        return this.data.getCountries().findCountryByCode(code).stream().findFirst();
    }
}
