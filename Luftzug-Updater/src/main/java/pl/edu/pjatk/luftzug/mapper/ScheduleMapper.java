package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.repository.ICatalogData;

public class ScheduleMapper implements IMapEntities<ScheduleDto, Schedule> {

    private final ICatalogData data;

    public ScheduleMapper(ICatalogData data){
        this.data = data;
    }

    @Override
    public Schedule map(ScheduleDto scheduleDto) {
        return map(scheduleDto, new Schedule());
    }

    @Override
    public Schedule map(ScheduleDto scheduleDto, Schedule schedule) {
        schedule.setDuration(scheduleDto.totalJourney().duration());
        schedule.setDepartureAirport(getAirport(scheduleDto.flight().departure().code()));
        schedule.setDepartureDateTime(scheduleDto.flight().departure().scheduledTimeLocal().localDateTime());
        schedule.setArriavalAirport(getAirport(scheduleDto.flight().arrival().code()));
        schedule.setArrivalDateTime(scheduleDto.flight().arrival().scheduledTimeLocal().localDateTime());
        schedule.setFlightNumber(scheduleDto.flight().marketingCarrier().flightNumber());
        schedule.setAirline(getAirline(scheduleDto.flight().marketingCarrier().code()));
        schedule.setAircraft(getAircraft(scheduleDto.flight().equipment().code()));
        return schedule;
    }

    public Aircraft getAircraft(String code){
        return data.getAircrafts().findAircraftByCode(code).getFirst();
    }
    public Airline getAirline(String code){
        return data.getAirlines().findAirlineByCode(code).getFirst();
    }
    public Airport getAirport(String code){
        return data.getAirports().findAirportByCode(code).getFirst();
    }
}
