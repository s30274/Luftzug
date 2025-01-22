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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleServiceGetTests {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private IMap<Schedule, ScheduleDto> scheduleToDtoMapper;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    @BeforeEach
    public void setup(){
        schedule = getFirstSchedule();
    }

    @Test
    @Order(1)
    public void getAllSchedules(){

        Schedule schedule1 = getSecondSchedule();

        // precondition
        given(scheduleRepository.findAll()).willReturn(List.of(schedule, schedule1));
        given(scheduleToDtoMapper.map(schedule)).willReturn(new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320"));
        given(scheduleToDtoMapper.map(schedule1)).willReturn(new ScheduleDto(2L, "2138", Duration.parse("PT1H37M"), "ZRH", LocalDateTime.parse("2025-01-20T16:00"), "WAW", LocalDateTime.parse("2025-01-20T17:37"), "LX", "320"));

        // action
        List<ScheduleDto> scheduleList = scheduleService.getAllSchedules();

        // verify
        assertThat(scheduleList).isNotNull();
        assertThat(scheduleList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getScheduleById(){
        // precondition
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(scheduleToDtoMapper.map(schedule)).willReturn(new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320"));

        // action
        ScheduleDto existingSchedule = scheduleService.getScheduleById(schedule.getId());

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(3)
    public void getScheduleByDepartureAirport(){
        // precondition
        given(scheduleRepository.findScheduleByDepartureAirportCode("WAW")).willReturn(List.of(schedule));
        given(scheduleToDtoMapper.map(schedule)).willReturn(new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320"));

        // action
        ScheduleDto existingSchedule = scheduleService.getScheduleByDepartureAirport(schedule.getDepartureAirport().getCode()).getFirst();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(4)
    public void getScheduleByArrivalAirport(){
        // precondition
        given(scheduleRepository.findScheduleByArrivalAirportCode("ZRH")).willReturn(List.of(schedule));
        given(scheduleToDtoMapper.map(schedule)).willReturn(new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320"));

        // action
        ScheduleDto existingSchedule = scheduleService.getScheduleByArrivalAirport(schedule.getArrivalAirport().getCode()).getFirst();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(5)
    public void getScheduleByFlightNumber(){
        // precondition
        given(scheduleRepository.findScheduleByFlightNumber("2137")).willReturn(List.of(schedule));
        given(scheduleToDtoMapper.map(schedule)).willReturn(new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320"));

        // action
        ScheduleDto existingSchedule = scheduleService.getScheduleByFlightNumber(schedule.getFlightNumber());

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    private Schedule getFirstSchedule(){
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

        Schedule schedule1 = new Schedule();
        schedule1.setId(1L);
        schedule1.setDuration(Duration.parse("PT1H37M"));
        schedule1.setDepartureAirport(departureAirport);
        schedule1.setDepartureDateTime(LocalDateTime.parse("2025-01-20T20:00"));
        schedule1.setArrivalAirport(arrivalAirport);
        schedule1.setArrivalDateTime(LocalDateTime.parse("2025-01-20T21:37"));
        schedule1.setAirline(airline);
        schedule1.setFlightNumber("2137");
        schedule1.setAircraft(aircraft);

        return schedule1;
    }

    private Schedule getSecondSchedule(){
        Aircraft aircraft = new Aircraft();
        aircraft.setId(2L);
        aircraft.setCode("320");
        aircraft.setName("Airbus A320");

        Airline airline = new Airline();
        airline.setId(2L);
        airline.setCode("LX");
        airline.setName("Swiss");

        Country departureCountry = new Country();
        departureCountry.setId(3);
        departureCountry.setCode("CH");
        departureCountry.setName("Switzerland");

        Country arrivalCountry = new Country();
        arrivalCountry.setId(4);
        arrivalCountry.setCode("PL");
        arrivalCountry.setName("Poland");

        Airport departureAirport = new Airport();
        departureAirport.setId(4L);
        departureAirport.setCode("ZRH");
        departureAirport.setName("Zurich Airport");
        departureAirport.setLongitude(21);
        departureAirport.setLatitude(37);
        departureAirport.setCountry(arrivalCountry);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setId(3L);
        arrivalAirport.setCode("WAW");
        arrivalAirport.setName("Warsaw Chopin Airport");
        arrivalAirport.setLongitude(52);
        arrivalAirport.setLatitude(20);
        arrivalAirport.setCountry(departureCountry);

        Schedule schedule1 = new Schedule();
        schedule1.setId(2L);
        schedule1.setDuration(Duration.parse("PT1H37M"));
        schedule1.setDepartureAirport(departureAirport);
        schedule1.setDepartureDateTime(LocalDateTime.parse("2025-01-20T16:00"));
        schedule1.setArrivalAirport(arrivalAirport);
        schedule1.setArrivalDateTime(LocalDateTime.parse("2025-01-20T17:37"));
        schedule1.setAirline(airline);
        schedule1.setFlightNumber("2138");
        schedule1.setAircraft(aircraft);

        return schedule1;
    }
}
