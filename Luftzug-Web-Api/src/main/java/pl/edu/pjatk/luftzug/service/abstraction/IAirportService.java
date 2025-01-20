package pl.edu.pjatk.luftzug.service.abstraction;

import pl.edu.pjatk.luftzug.contract.AirportDto;

import java.util.List;
import java.util.Optional;

public interface IAirportService {
    List<AirportDto> getAllAirports();

    AirportDto getAirportById(Long id);

    AirportDto getAirportByCode(String code);
}
