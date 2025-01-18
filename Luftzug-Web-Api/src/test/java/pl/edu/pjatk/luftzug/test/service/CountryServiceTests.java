package pl.edu.pjatk.luftzug.test.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.repository.CountryRepository;
import pl.edu.pjatk.luftzug.service.CountryService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CountryServiceTests {
    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    private Country country;

    @BeforeEach
    public void setup(){
        country = new Country();
        country.setId(1);
        country.setCode("DE");
        country.setName("Germany");
    }

    @Test
    @Order(1)
    public void getAllCountries(){
        Country country1 = new Country();
        country1.setCode("FR");
        country1.setName("France");

        // precondition
        given(countryRepository.findAll()).willReturn(List.of(country, country1));

        // action
        List<Country> countryList = countryService.getAllCountries();

        // verify
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    public void getCountryById(){
        // precondition
        given(countryRepository.findById(1)).willReturn(Optional.of(country));

        // action
        Country existingCountry = countryService.getCountryById(country.getId()).get();

        // verify
        assertThat(existingCountry).isNotNull();
    }

    @Test
    @Order(3)
    public void getCountryByCode(){
        // precondition
        given(countryRepository.findCountryByCode("DE")).willReturn(List.of(country));

        // action
        Country existingCountry = countryService.getCountryByCode(country.getCode()).get();

        // verify
        assertThat(existingCountry).isNotNull();
    }
}
