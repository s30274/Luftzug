package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjatk.luftzug.exception.AirlineNotFoundException;
import pl.edu.pjatk.luftzug.model.Airline;
import pl.edu.pjatk.luftzug.service.abstraction.IAirlineService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/reference/airline")
public class AirlineController {
    private final IAirlineService airlineService;

    public AirlineController(IAirlineService airlineService){
        this.airlineService = airlineService;
    }

    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines(){
        List<Airline> list = this.airlineService.getAllAirlines();
        if(list.isEmpty()){
            throw new AirlineNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Airline>> getAirlineById(@PathVariable Long id){
        Optional<Airline> airline = this.airlineService.getAirlineById(id);
        if(airline.isEmpty()){
            throw new AirlineNotFoundException();
        }
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<Airline>> getAirlineByCode(@PathVariable String code){
        Optional<Airline> airline = this.airlineService.getAirlineByCode(code);
        if(airline.isEmpty()){
            throw new AirlineNotFoundException();
        }
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }
}
