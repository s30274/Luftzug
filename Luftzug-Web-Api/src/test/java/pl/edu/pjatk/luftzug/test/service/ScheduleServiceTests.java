package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.ScheduleRepository;
import pl.edu.pjatk.luftzug.service.ScheduleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleServiceTests {
    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

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
        schedule.setDuration(Duration.ZERO);
        schedule.setDepartureAirport(departureAirport);
        schedule.setDepartureDateTime(LocalDateTime.now());
        schedule.setArrivalAirport(arrivalAirport);
        schedule.setArrivalDateTime(LocalDateTime.now());
        schedule.setAirline(airline);
        schedule.setFlightNumber("2137");
        schedule.setAircraft(aircraft);
    }

    @Test
    @Order(1)
    public void getAllSchedules(){
        Schedule schedule1 = new Schedule();
        schedule1.setId(2L);
        schedule1.setFlightNumber("2138");

        // precondition
        given(scheduleRepository.findAll()).willReturn(List.of(schedule, schedule1));

        // action
        List<Schedule> scheduleList = scheduleService.getAllSchedules();

        // verify
        assertThat(scheduleList).isNotNull();
        assertThat(scheduleList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getScheduleById(){
        // precondition
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        // action
        Schedule existingSchedule = scheduleService.getScheduleById(schedule.getId()).get();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(3)
    public void getScheduleByDepartureAirport(){
        // precondition
        given(scheduleRepository.findScheduleByDepartureAirportCode("WAW")).willReturn(List.of(schedule));

        // action
        Schedule existingSchedule = scheduleService.getScheduleByDepartureAirport(schedule.getDepartureAirport().getCode()).getFirst();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(4)
    public void getScheduleByArrivalAirport(){
        // precondition
        given(scheduleRepository.findScheduleByArrivalAirportCode("ZRH")).willReturn(List.of(schedule));

        // action
        Schedule existingSchedule = scheduleService.getScheduleByArrivalAirport(schedule.getArrivalAirport().getCode()).getFirst();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(5)
    public void getScheduleByFlightNumber(){
        // precondition
        given(scheduleRepository.findScheduleByFlightNumber("2137")).willReturn(List.of(schedule));

        // action
        Schedule existingSchedule = scheduleService.getScheduleByFlightNumber(schedule.getFlightNumber()).getFirst();

        // verify
        assertThat(existingSchedule).isNotNull();
    }

    @Test
    @Order(6)
    public void saveSchedule(){
        // precondition
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        // action
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);

        // verify
        assertThat(savedSchedule).isNotNull();
    }

    @Test
    @Order(7)
    public void updateSchedule(){
        Schedule newSchedule = schedule;
        newSchedule.setFlightNumber("2139");

        // precondition
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        // action
        Schedule updatedSchedule = scheduleService.updateSchedule(newSchedule, schedule);

        // verify
        assertThat(updatedSchedule.getFlightNumber()).isEqualTo("2139");
    }

    @Test
    @Order(8)
    public void deleteSchedule(){
        // prediction
        willDoNothing().given(scheduleRepository).deleteById(schedule.getId());

        // action
        scheduleRepository.deleteById(schedule.getId());

        // verify
        verify(scheduleRepository, times(1)).deleteById(schedule.getId());
    }
}
