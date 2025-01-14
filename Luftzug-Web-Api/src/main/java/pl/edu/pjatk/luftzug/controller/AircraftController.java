package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjatk.luftzug.exception.AircraftNotFoundException;
import pl.edu.pjatk.luftzug.model.Aircraft;
import pl.edu.pjatk.luftzug.service.abstraction.IAircraftService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/reference/aircraft")
public class AircraftController {
    private final IAircraftService aircraftService;

    public AircraftController(IAircraftService aircraftService){
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAllAircrafts(){
        List<Aircraft> list = this.aircraftService.getAllAircrafts();
        if(list.isEmpty()){
            throw new AircraftNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Aircraft>> getAircraftById(@PathVariable Long id){
        Optional<Aircraft> aircraft = this.aircraftService.getAircraftById(id);
        if(aircraft.isEmpty()){
            throw new AircraftNotFoundException();
        }
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<Aircraft>> getAircraftByCode(@PathVariable String code){
        Optional<Aircraft> aircraft = this.aircraftService.getAircraftByCode(code);
        if(aircraft.isEmpty()){
            throw new AircraftNotFoundException();
        }
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }
}
