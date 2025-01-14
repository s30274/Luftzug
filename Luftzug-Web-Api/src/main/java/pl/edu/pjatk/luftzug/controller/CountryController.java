package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjatk.luftzug.exception.CountryNotFoundException;
import pl.edu.pjatk.luftzug.model.Country;
import pl.edu.pjatk.luftzug.service.abstraction.ICountryService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/reference/country")
public class CountryController {
    private final ICountryService countryService;

    public CountryController(ICountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> list = this.countryService.getAllCountries();
        if(list.isEmpty()){
            throw new CountryNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Country>> getCountryById(@PathVariable Integer id){
        Optional<Country> country = this.countryService.getCountryById(id);
        if(country.isEmpty()){
            throw new CountryNotFoundException();
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<Country>> getCountryByCode(@PathVariable String code){
        Optional<Country> country = this.countryService.getCountryByCode(code);
        if(country.isEmpty()){
            throw new CountryNotFoundException();
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
}
