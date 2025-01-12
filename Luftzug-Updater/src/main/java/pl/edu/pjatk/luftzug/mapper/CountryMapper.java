package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.CountryDto;
import pl.edu.pjatk.luftzug.model.Country;

public class CountryMapper implements IMapEntities<CountryDto, Country> {
    @Override
    public Country map(CountryDto countryDto) {
        return map(countryDto, new Country());
    }

    @Override
    public Country map(CountryDto countryDto, Country country) {
        country.setCode(countryDto.code());
        country.setName(extractName(countryDto));
        return country;
    }
}
