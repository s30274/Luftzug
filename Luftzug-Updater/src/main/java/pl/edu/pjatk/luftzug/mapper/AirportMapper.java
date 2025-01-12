package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.AirportDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

public class AirportMapper implements IMapEntities<AirportDto, Airport> {
    private final ICatalogData data;
    public AirportMapper(ICatalogData data){
        this.data = data;
    }
    @Override
    public Airport map(AirportDto airportDto) {
        return map(airportDto, new Airport());
    }

    @Override
    public Airport map(AirportDto airportDto, Airport airport) {
        airport.setCode(airportDto.code());
        airport.setName(extractName(airportDto));
        airport.setLatitude(airportDto.position().coordinate().latitude());
        airport.setLongitude(airportDto.position().coordinate().longitude());
        airport.setCountry(getCountry(airportDto.countryCode()));
        return airport;
    }

    public Country getCountry(String code){
        return data.getCountries().findCountryByCode(code).getFirst();
    }
}
