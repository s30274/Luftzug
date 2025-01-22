package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ScheduleRepository;
import pl.edu.pjatk.luftzug.service.ScheduleService;
import pl.edu.pjatk.luftzug.service.mappers.IMap;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleServicePostPutDeleteTests {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private IMap<ScheduleDto, Schedule> dtoToScheduleMapper;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    private ScheduleDto dto;

    @BeforeEach
    public void setup(){
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setCode("320");
        aircraft.setName("Airbus A320");

        Airline airline = new Airline();
        airline.setId(1L);
        airline.setCode("LX");
        airline.setName("Swiss");

        Country departureCountry = new Country();
        departureCountry.setId(1);
        departureCountry.setCode("PL");
        departureCountry.setName("Poland");

        Country arrivalCountry = new Country();
        arrivalCountry.setId(2);
        arrivalCountry.setCode("CH");
        arrivalCountry.setName("Switzerland");

        Airport departureAirport = new Airport();
        departureAirport.setId(1L);
        departureAirport.setCode("WAW");
        departureAirport.setName("Warsaw Chopin Airport");
        departureAirport.setLongitude(52);
        departureAirport.setLatitude(20);
        departureAirport.setCountry(departureCountry);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setId(2L);
        arrivalAirport.setCode("ZRH");
        arrivalAirport.setName("Zurich Airport");
        arrivalAirport.setLongitude(21);
        arrivalAirport.setLatitude(37);
        arrivalAirport.setCountry(arrivalCountry);

        schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDuration(Duration.parse("PT1H37M"));
        schedule.setDepartureAirport(departureAirport);
        schedule.setDepartureDateTime(LocalDateTime.parse("2025-01-20T20:00"));
        schedule.setArrivalAirport(arrivalAirport);
        schedule.setArrivalDateTime(LocalDateTime.parse("2025-01-20T21:37"));
        schedule.setAirline(airline);
        schedule.setFlightNumber("2137");
        schedule.setAircraft(aircraft);

        dto = new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320");
    }

    @Test
    @Order(1)
    public void saveSchedule(){
        // precondition
        given(scheduleRepository.save(schedule)).willReturn(schedule);
        given(dtoToScheduleMapper.map(dto)).willReturn(schedule);

        // action
        Schedule savedSchedule = scheduleService.saveSchedule(dto);

        // verify
        assertThat(savedSchedule).isNotNull();
    }

    @Test
    @Order(2)
    public void updateSchedule(){
        Schedule newSchedule = schedule;
        newSchedule.setFlightNumber("2139");
        dto.setFlightNumber("2139");

        // precondition
        given(dtoToScheduleMapper.map(dto)).willReturn(schedule);

        // action
        Schedule updatedSchedule = scheduleService.updateSchedule(dto);

        // verify
        assertThat(updatedSchedule.getFlightNumber()).isEqualTo("2139");
    }

    @Test
    @Order(3)
    public void deleteSchedule(){
        // prediction
        willDoNothing().given(scheduleRepository).deleteById(schedule.getId());

        // action
        scheduleRepository.deleteById(dto.getId());

        // verify
        verify(scheduleRepository, times(1)).deleteById(schedule.getId());
    }
}
