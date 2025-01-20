package pl.edu.pjatk.luftzug.service.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

@Component
class ScheduleDtoMapper implements IMap<Schedule, ScheduleDto> {

    @Override
    public ScheduleDto map(Schedule schedule) {
        return new ScheduleDto(schedule.getId(),
                schedule.getFlightNumber(),
                schedule.getDuration(),
                schedule.getDepartureAirport().getCode(),
                schedule.getDepartureDateTime(),
                schedule.getArrivalAirport().getCode(),
                schedule.getArrivalDateTime(),
                schedule.getAirline().getCode(),
                schedule.getAircraft().getCode());
    }
}

@Component
@RequiredArgsConstructor
class ScheduleEntityMapper implements IMap<ScheduleDto, Schedule> {
    private final ICatalogData data;

    @Override
    public Schedule map(ScheduleDto dto) {
        Schedule schedule = new Schedule();
        schedule.setFlightNumber(dto.getFlightNumber());
        schedule.setDuration(dto.getDuration());
        schedule.setDepartureAirport(getAirportByCode(dto.getDepartureAirportCode()));
        schedule.setDepartureDateTime(dto.getDepartureDateTime());
        schedule.setArrivalAirport(getAirportByCode(dto.getArrivalAirportCode()));
        schedule.setArrivalDateTime(dto.getArrivalDateTime());
        schedule.setAirline(getAirlineByCode(dto.getAirlineCode()));
        schedule.setAircraft(getAircraftByCode(dto.getAircraftCode()));
        return schedule;
    }

    private Airport getAirportByCode(String code){
        return data.getAirports().findAirportByCode(code).getFirst();
    }

    private Airline getAirlineByCode(String code){
        return data.getAirlines().findAirlineByCode(code).getFirst();
    }

    private Aircraft getAircraftByCode(String code){
        return data.getAircrafts().findAircraftByCode(code).getFirst();
    }
}