package pl.edu.pjatk.luftzug.test.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.repository.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ScheduleRepositoryTests {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveSchedule() {

        Aircraft aircraft = new Aircraft();
        aircraft.setCode("320");
        aircraft.setName("Airbus A320");
        aircraftRepository.save(aircraft);

        Airline airline = new Airline();
        airline.setCode("LX");
        airline.setName("Swiss");
        airlineRepository.save(airline);

        Country departureCountry = new Country();
        departureCountry.setCode("PL");
        departureCountry.setName("Poland");
        countryRepository.save(departureCountry);

        Country arrivalCountry = new Country();
        arrivalCountry.setCode("CH");
        arrivalCountry.setName("Switzerland");
        countryRepository.save(arrivalCountry);

        Airport departureAirport = new Airport();
        departureAirport.setCode("WAW");
        departureAirport.setName("Warsaw Chopin Airport");
        departureAirport.setLongitude(52);
        departureAirport.setLatitude(20);
        departureAirport.setCountry(departureCountry);
        airportRepository.save(departureAirport);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setCode("ZRH");
        arrivalAirport.setName("Zurich Airport");
        arrivalAirport.setLongitude(21);
        arrivalAirport.setLatitude(37);
        arrivalAirport.setCountry(arrivalCountry);
        airportRepository.save(arrivalAirport);

        Schedule schedule = new Schedule();
        schedule.setDuration(Duration.ZERO);
        schedule.setDepartureAirport(departureAirport);
        schedule.setDepartureDateTime(LocalDateTime.now());
        schedule.setArrivalAirport(arrivalAirport);
        schedule.setArrivalDateTime(LocalDateTime.now());
        schedule.setAirline(airline);
        schedule.setFlightNumber("2137");
        schedule.setAircraft(aircraft);

        // action
        scheduleRepository.save(schedule);

        // verify
        assertThat(schedule.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    public void getScheduleById() {

        // action
        Schedule schedule = scheduleRepository.findById(1L).get();

        // verify
        assertThat(schedule.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getScheduleByFlightNumber() {

        // action
        Schedule schedule = scheduleRepository.findScheduleByFlightNumber("2137").getFirst();

        // verify
        assertThat(schedule.getFlightNumber()).isEqualTo("2137");
    }

    @Test
    @Order(4)
    public void getScheduleByDepartureAirportCode() {

        // action
        Schedule schedule = scheduleRepository.findScheduleByDepartureAirportCode("WAW").getFirst();

        // verify
        assertThat(schedule.getDepartureAirport().getCode()).isEqualTo("WAW");
    }

    @Test
    @Order(5)
    public void getScheduleByArrivalAirportCode() {

        // action
        Schedule schedule = scheduleRepository.findScheduleByArrivalAirportCode("ZRH").getFirst();

        // verify
        assertThat(schedule.getArrivalAirport().getCode()).isEqualTo("ZRH");
    }

    @Test
    @Order(6)
    public void getScheduleList() {

        // action
        List<Schedule> schedules = scheduleRepository.findAll();

        // verify
        assertThat(schedules.size()).isGreaterThan(0);
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void updateSchedule() {

        // action
        Schedule schedule = scheduleRepository.findById(1L).get();
        schedule.setFlightNumber("2138");
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // verify
        assertThat(schedule.getFlightNumber()).isEqualTo("2138");
    }

    @Test
    @Order(8)
    @Rollback(value = false)
    public void deleteScheduleById(){

        // action
        scheduleRepository.deleteById(1L);
        Optional<Schedule> schedule = scheduleRepository.findById(1L);

        // verify
        assertThat(schedule).isEmpty();

        airportRepository.deleteById(2L);
        airportRepository.deleteById(1L);
        countryRepository.deleteById(2);
        countryRepository.deleteById(1);
        airlineRepository.deleteById(1L);
        aircraftRepository.deleteById(1L);
    }
}