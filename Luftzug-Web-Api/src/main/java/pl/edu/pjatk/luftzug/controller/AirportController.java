package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjatk.luftzug.exception.AircraftNotFoundException;
import pl.edu.pjatk.luftzug.exception.AirportNotFoundException;
import pl.edu.pjatk.luftzug.model.Airport;
import pl.edu.pjatk.luftzug.service.abstraction.IAirportService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/reference/airport")
public class AirportController {
    private final IAirportService airportService;

    public AirportController(IAirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports(){
        List<Airport> list = this.airportService.getAllAirports();
        if(list.isEmpty()){
            throw new AircraftNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Airport>> getAirportById(@PathVariable Long id){
        Optional<Airport> airport = this.airportService.getAirportById(id);
        if(airport.isEmpty()){
            throw new AirportNotFoundException();
        }
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<Airport>> getAirportByCode(@PathVariable String code){
        Optional<Airport> airport = this.airportService.getAirportByCode(code);
        if(airport.isEmpty()){
            throw new AirportNotFoundException();
        }
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }
}
