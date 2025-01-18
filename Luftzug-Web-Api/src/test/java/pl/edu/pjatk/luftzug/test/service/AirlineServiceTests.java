package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.repository.AirlineRepository;
import pl.edu.pjatk.luftzug.service.AirlineService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AirlineServiceTests {
    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    private Airline airline;

    @BeforeEach
    public void setup(){
        airline = new Airline();
        airline.setId(1L);
        airline.setCode("LX");
        airline.setName("Swiss");
    }

    @Test
    @Order(1)
    public void getAllAirlines(){
        Airline airline1 = new Airline();
        airline1.setCode("LH");
        airline1.setName("Lufthansa");

        // precondition
        given(airlineRepository.findAll()).willReturn(List.of(airline, airline1));

        // action
        List<Airline> airlineList = airlineService.getAllAirlines();

        // verify
        assertThat(airlineList).isNotNull();
        assertThat(airlineList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getAirlineById(){
        // precondition
        given(airlineRepository.findById(1L)).willReturn(Optional.of(airline));

        // action
        Airline existingAirline = airlineService.getAirlineById(airline.getId()).get();

        // verify
        assertThat(existingAirline).isNotNull();
    }

    @Test
    @Order(3)
    public void getAirlineByCode(){
        // precondition
        given(airlineRepository.findAirlineByCode("LX")).willReturn(List.of(airline));

        // action
        Airline existingAirline = airlineService.getAirlineByCode(airline.getCode()).get();

        // verify
        assertThat(existingAirline).isNotNull();
    }
}
