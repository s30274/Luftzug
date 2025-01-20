package pl.edu.pjatk.luftzug.service.mappers;

import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Schedule;

public interface IMappers {
    IMap<Airport, AirportDto> getAirportToDtoMapper();
    IMap<AirportDto, Airport> getDtoToAirportMapper();
    IMap<Schedule, ScheduleDto> getScheduleToDtoMapper();
    IMap<ScheduleDto, Schedule> getDtoToScheduleMapper();
}
