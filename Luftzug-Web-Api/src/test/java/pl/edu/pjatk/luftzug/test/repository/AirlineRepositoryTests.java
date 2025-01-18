package pl.edu.pjatk.luftzug.test.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.repository.AirlineRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AirlineRepositoryTests {

    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAirlineTest() {

        Airline airline = new Airline();
        airline.setCode("LX");
        airline.setName("Swiss");

        // action
        airlineRepository.save(airline);

        // verify
        assertThat(airline.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    public void getAirlineTest() {

        // action
        Airline airline = airlineRepository.findById(1L).get();

        // verify
        assertThat(airline.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getAirlineListTest() {

        // action
        List<Airline> airlines = airlineRepository.findAll();

        // verify
        assertThat(airlines.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAirlineTest() {

        // action
        Airline airline = airlineRepository.findById(1L).get();
        airline.setName("Luxemburg Airlines");
        Airline updatedAirline = airlineRepository.save(airline);

        // verify
        assertThat(airline.getName()).isEqualTo("Luxemburg Airlines");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAirlineByIdTest(){

        // action
        airlineRepository.deleteById(1L);
        Optional<Airline> airline = airlineRepository.findById(1L);

        // verify
        assertThat(airline).isEmpty();
    }
}