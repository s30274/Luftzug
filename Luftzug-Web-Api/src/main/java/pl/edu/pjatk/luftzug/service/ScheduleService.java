package pl.edu.pjatk.luftzug.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ScheduleRepository;
import pl.edu.pjatk.luftzug.service.abstraction.IScheduleService;
import pl.edu.pjatk.luftzug.service.mappers.IMap;
import pl.edu.pjatk.luftzug.service.mappers.IMappers;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {
    private final ScheduleRepository repository;
    private final IMap<Schedule, ScheduleDto> scheduleToDtoMapper;
    private final IMap<ScheduleDto, Schedule> dtoToScheduleMapper;

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return this.repository.findAll()
                .stream()
                .map(schedule -> scheduleToDtoMapper.map(schedule))
                .toList();
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        Optional<Schedule> optionalSchedule = this.repository.findById(id);
        if(optionalSchedule.isPresent()){
            return scheduleToDtoMapper.map(optionalSchedule.get());
        } else {
            return null;
        }
    }

    @Override
    public List<ScheduleDto> getScheduleByDepartureAirport(String departureAirportCode) {
        return this.repository.findScheduleByDepartureAirportCode(departureAirportCode)
                .stream()
                .map(schedule -> scheduleToDtoMapper.map(schedule))
                .toList();
    }

    @Override
    public List<ScheduleDto> getScheduleByArrivalAirport(String arrivalAirportCode) {
        return this.repository.findScheduleByArrivalAirportCode(arrivalAirportCode)
                .stream()
                .map(schedule -> scheduleToDtoMapper.map(schedule))
                .toList();
    }

    @Override
    public ScheduleDto getScheduleByFlightNumber(String flightNumber) {
        Optional<Schedule> optionalSchedule = this.repository.findScheduleByFlightNumber(flightNumber).stream().findFirst();
        if(optionalSchedule.isPresent()){
            return scheduleToDtoMapper.map(optionalSchedule.get());
        } else {
            return null;
        }
    }

    @Override
    public Schedule saveSchedule(ScheduleDto dto) {
        Schedule schedule = dtoToScheduleMapper.map(dto);
        this.repository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(ScheduleDto dto) {
        return saveSchedule(dto);
    }

    @Override
    public void deleteSchedule(Long id) {
        this.repository.deleteById(id);
    }
}
