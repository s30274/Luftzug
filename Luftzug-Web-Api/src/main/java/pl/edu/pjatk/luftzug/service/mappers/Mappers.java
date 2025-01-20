package pl.edu.pjatk.luftzug.service.mappers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Schedule;

@Component
@RequiredArgsConstructor
@Getter
@Accessors
public class Mappers implements IMappers{
    private final IMap<Airport, AirportDto> airportToDtoMapper;
    private final IMap<AirportDto, Airport> dtoToAirportMapper;
    private final IMap<Schedule, ScheduleDto> scheduleToDtoMapper;
    private final IMap<ScheduleDto, Schedule> dtoToScheduleMapper;
}
