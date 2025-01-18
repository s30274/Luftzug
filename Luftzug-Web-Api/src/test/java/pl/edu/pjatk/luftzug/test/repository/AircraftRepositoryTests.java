package pl.edu.pjatk.luftzug.test.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.repository.AircraftRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AircraftRepositoryTests {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAircraftTest() {

        Aircraft aircraft = new Aircraft();
        aircraft.setCode("2137");
        aircraft.setName("Airbus A2137");

        // action
        aircraftRepository.save(aircraft);

        // verify
        assertThat(aircraft.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    public void getAircraftTest() {

        // action
        Aircraft aircraft = aircraftRepository.findById(1L).get();

        // verify
        assertThat(aircraft.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getAircraftListTest() {

        // action
        List<Aircraft> aircrafts = aircraftRepository.findAll();

        // verify
        assertThat(aircrafts.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAircraftTest() {

        // action
        Aircraft aircraft = aircraftRepository.findById(1L).get();
        aircraft.setName("Boeing 2137");
        Aircraft updatedAircraft = aircraftRepository.save(aircraft);

        // verify
        assertThat(aircraft.getName()).isEqualTo("Boeing 2137");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAircraftByIdTest(){

        // action
        aircraftRepository.deleteById(1L);
        Optional<Aircraft> aircraft = aircraftRepository.findById(1L);

        // verify
        assertThat(aircraft).isEmpty();
    }
}
