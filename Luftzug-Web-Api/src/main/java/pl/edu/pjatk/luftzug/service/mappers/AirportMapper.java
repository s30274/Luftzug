package pl.edu.pjatk.luftzug.service.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

@Component
class AirportDtoMapper implements IMap<Airport, AirportDto> {

    @Override
    public AirportDto map(Airport airport) {
        return new AirportDto(airport.getId(),
                airport.getCode(),
                airport.getName(),
                airport.getLatitude(),
                airport.getLongitude(),
                airport.getCountry().getCode());
    }
}

@Component
@RequiredArgsConstructor
class AirportEntityMapper implements IMap<AirportDto, Airport> {
    private final ICatalogData data;

    @Override
    public Airport map(AirportDto dto) {
        Airport airport = new Airport();
        airport.setCode(dto.getCode());
        airport.setName(dto.getName());
        airport.setLatitude(dto.getLatitude());
        airport.setLongitude(dto.getLongitude());
        airport.setCountry(getCountryByCode(dto.getCountryCode()));
        return airport;
    }

    private Country getCountryByCode(String code){
        return data.getCountries().findCountryByCode(code).getFirst();
    }
}