package pl.edu.pjatk.luftzug.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.repository.AirportRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AirportServiceTests {
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    public void setup(){
        airport = new Airport();
        airport.setId(1L);
        airport.setCode("ZRH");
        airport.setName("Zurich");
    }

    @Test
    @Order(1)
    public void getAllAirports(){
        Airport airport1 = new Airport();
        airport1.setCode("WAW");
        airport1.setName("Warsaw");

        // precondition
        given(airportRepository.findAll()).willReturn(List.of(airport, airport1));

        // action
        List<Airport> airportList = airportService.getAllAirports();

        // verify
        assertThat(airportList).isNotNull();
        assertThat(airportList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getAirportById(){
        // precondition
        given(airportRepository.findById(1L)).willReturn(Optional.of(airport));

        // action
        Airport existingAirport = airportService.getAirportById(airport.getId()).get();

        // verify
        assertThat(existingAirport).isNotNull();
    }

    @Test
    @Order(3)
    public void getAirportByCode(){
        // precondition
        given(airportRepository.findAirportByCode("ZRH")).willReturn(List.of(airport));

        // action
        Airport existingAirport = airportService.getAirportByCode(airport.getCode()).get();

        // verify
        assertThat(existingAirport).isNotNull();
    }
}
