package pl.edu.pjatk.luftzug.test.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.service.mappers.IMap;
import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.service.mappers.Mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MappersTests {

    @Mock
    private IMap<Airport, AirportDto> airportToDtoMapper;

    @Mock
    private IMap<AirportDto, Airport> dtoToAirportMapper;

    @Mock
    private IMap<Schedule, ScheduleDto> scheduleToDtoMapper;

    @Mock
    private IMap<ScheduleDto, Schedule> dtoToScheduleMapper;

    @InjectMocks
    private Mappers mappers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAirportToDtoMapper() {

        // action
        IMap<Airport, AirportDto> result = mappers.getAirportToDtoMapper();

        // verify
        assertNotNull(result);
    }

    @Test
    void getDtoToAirportMapper() {

        // action
        IMap<AirportDto, Airport> result = mappers.getDtoToAirportMapper();

        // verify
        assertNotNull(result);
    }

    @Test
    void getScheduleToDtoMapper() {

        // action
        IMap<Schedule, ScheduleDto> result = mappers.getScheduleToDtoMapper();

        // verify
        assertNotNull(result);
    }

    @Test
    void getDtoToScheduleMapper() {

        // action
        IMap<ScheduleDto, Schedule> result = mappers.getDtoToScheduleMapper();

        // verify
        assertNotNull(result);
    }
}