package pl.edu.pjatk.luftzug.test.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.AirportRepository;
import pl.edu.pjatk.luftzug.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AirportRepositoryTests {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAirporTest() {

        Country country = new Country();
        country.setCode("PL");
        country.setName("Poland");

        countryRepository.save(country);

        Airport airport = new Airport();
        airport.setCode("GDA");
        airport.setName("Gdansk Lech Walesa Airport");
        airport.setCountry(country);

        // action
        airportRepository.save(airport);

        // verify
        assertThat(airport.getId()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    public void getAirportTest() {

        // action
        Airport airport = airportRepository.findById(1L).get();

        // verify
        assertThat(airport.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getAirportListTest() {

        // action
        List<Airport> airports = airportRepository.findAll();

        // verify
        assertThat(airports.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAirportTest() {

        // action
        Airport airport = airportRepository.findById(1L).get();
        airport.setName("Danzig Lech Walesa Airport");
        Airport updatedAirport = airportRepository.save(airport);

        // verify
        assertThat(airport.getName()).isEqualTo("Danzig Lech Walesa Airport");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAirportByIdTest(){

        // action
        airportRepository.deleteById(1L);
        Optional<Airport> airport = airportRepository.findById(1L);

        // verify
        assertThat(airport).isEmpty();
    }
}