package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.repository.AircraftRepository;
import pl.edu.pjatk.luftzug.service.AircraftService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AircraftServiceTests {
    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    private Aircraft aircraft;

    @BeforeEach
    public void setup(){
        aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setCode("320");
        aircraft.setName("Airbus A320");
    }

    @Test
    @Order(1)
    public void getAllAircrafts(){
        Aircraft aircraft1 = new Aircraft();
        aircraft1.setCode("747");
        aircraft1.setName("Boeing 747");

        // precondition
        given(aircraftRepository.findAll()).willReturn(List.of(aircraft, aircraft1));

        // action
        List<Aircraft> aircraftList = aircraftService.getAllAircrafts();

        // verify
        assertThat(aircraftList).isNotNull();
        assertThat(aircraftList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getAircraftById(){
        // precondition
        given(aircraftRepository.findById(1L)).willReturn(Optional.of(aircraft));

        // action
        Aircraft existingAircraft = aircraftService.getAircraftById(aircraft.getId()).get();

        // verify
        assertThat(existingAircraft).isNotNull();
    }

    @Test
    @Order(3)
    public void getAircraftByCode(){
        // precondition
        given(aircraftRepository.findAircraftByCode("320")).willReturn(List.of(aircraft));

        // action
        Aircraft existingAircraft = aircraftService.getAircraftByCode(aircraft.getCode()).get();

        // verify
        assertThat(existingAircraft).isNotNull();
    }
}
