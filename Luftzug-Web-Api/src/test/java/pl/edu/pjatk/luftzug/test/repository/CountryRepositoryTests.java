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
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CountryRepositoryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCountry() {

        Country country = new Country();
        country.setCode("CH");
        country.setName("Switzerland");

        // action
        countryRepository.save(country);

        // verify
        assertThat(country.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getCountryById() {

        // action
        Country country = countryRepository.findById(1).get();

        // verify
        assertThat(country.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getCountryByCode() {

        // action
        Country country = countryRepository.findCountryByCode("CH").getFirst();

        // verify
        assertThat(country.getCode()).isEqualTo("CH");
    }

    @Test
    @Order(4)
    public void getCountryList() {

        // action
        List<Country> countries = countryRepository.findAll();

        // verify
        assertThat(countries.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void updateCountry() {

        // action
        Country country = countryRepository.findById(1).get();
        country.setName("Swiss");
        Country updatedCountry = countryRepository.save(country);

        // verify
        assertThat(country.getName()).isEqualTo("Swiss");
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteCountryById(){

        // action
        countryRepository.deleteById(1);
        Optional<Country> country = countryRepository.findById(1);

        // verify
        assertThat(country).isEmpty();
    }
}