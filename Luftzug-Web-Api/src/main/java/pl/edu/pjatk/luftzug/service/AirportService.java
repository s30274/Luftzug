package pl.edu.pjatk.luftzug.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.repository.AirportRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IAirportService;
import pl.edu.pjatk.luftzug.service.mappers.IMap;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService implements IAirportService {
    private final AirportRepository repository;
    private final IMap<Airport, AirportDto> airportToDtoMapper;

    @Override
    public List<AirportDto> getAllAirports() {
        return this.repository.findAll()
                .stream()
                .map(airport -> airportToDtoMapper.map(airport))
                .toList();
    }

    @Override
    public AirportDto getAirportById(Long id) {
        Optional<Airport> optionalAirport = this.repository.findById(id);
        if(optionalAirport.isPresent()) {
            return airportToDtoMapper.map(optionalAirport.get());
        } else {
            return null;
        }
    }

    @Override
    public AirportDto getAirportByCode(String code) {
        Optional<Airport> optionalAirport = this.repository.findAirportByCode(code).stream().findFirst();
        if(optionalAirport.isPresent()) {
            return airportToDtoMapper.map(optionalAirport.get());
        } else {
            return null;
        }
    }
}
