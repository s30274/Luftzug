package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.contract.AirportDto;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.AirportRepository;
import pl.edu.pjatk.luftzug.service.AirportService;
import pl.edu.pjatk.luftzug.service.mappers.IMap;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AirportServiceTests {
    @Mock
    private AirportRepository airportRepository;

    @Mock
    private IMap<Airport, AirportDto> airportToDtoMapper;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    public void setup(){
        Country country = new Country();
        country.setId(1);
        country.setCode("CH");
        country.setName("Switzerland");

        airport = new Airport();
        airport.setId(1L);
        airport.setCode("ZRH");
        airport.setName("Zurich");
        airport.setLatitude(21);
        airport.setLongitude(37);
        airport.setCountry(country);
    }

    @Test
    @Order(1)
    public void getAllAirports(){
        Country country1 = new Country();
        country1.setId(2);
        country1.setCode("PL");
        country1.setName("Poland");

        Airport airport1 = new Airport();
        airport1.setId(2L);
        airport1.setCode("WAW");
        airport1.setName("Warsaw");
        airport1.setLatitude(51);
        airport1.setLongitude(61);
        airport1.setCountry(country1);

        // precondition
        given(airportRepository.findAll()).willReturn(List.of(airport, airport1));
        given(airportToDtoMapper.map(airport)).willReturn(new AirportDto(1L, "ZRH", "Zurich", 21, 37, "CH"));
        given(airportToDtoMapper.map(airport1)).willReturn(new AirportDto(2L, "WAW", "Warsaw", 51, 61, "PL"));

        // action
        List<AirportDto> airportList = airportService.getAllAirports();

        // verify
        assertThat(airportList).isNotNull();
        assertThat(airportList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getAirportById(){
        // precondition
        given(airportRepository.findById(1L)).willReturn(Optional.of(airport));
        given(airportToDtoMapper.map(airport)).willReturn(new AirportDto(1L, "ZRH", "Zurich", 21, 37, "CH"));

        // action
        System.out.println(airport);
        List<AirportDto> airportList = airportService.getAllAirports();
        System.out.println(airportList.size());
        AirportDto existingAirport = airportService.getAirportById(1L);
        System.out.println(existingAirport);

        // verify
        assertThat(existingAirport).isNotNull();
    }

    @Test
    @Order(3)
    public void getAirportByCode(){
        // precondition
        given(airportRepository.findAirportByCode("ZRH")).willReturn(List.of(airport));
        given(airportToDtoMapper.map(airport)).willReturn(new AirportDto(1L, "ZRH", "Zurich", 21, 37, "CH"));

        // action
        AirportDto existingAirport = airportService.getAirportByCode(airport.getCode());

        // verify
        assertThat(existingAirport).isNotNull();
    }
}
