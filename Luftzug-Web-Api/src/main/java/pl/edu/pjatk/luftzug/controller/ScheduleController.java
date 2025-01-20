package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.exception.ScheduleAlreadyExistsException;
import pl.edu.pjatk.luftzug.exception.ScheduleNotFoundException;
import pl.edu.pjatk.luftzug.exception.ScheduleWrongInputException;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.service.abstraction.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final IScheduleService scheduleService;
    private final IPdfService pdfService;

    public ScheduleController(IScheduleService scheduleService, IPdfService pdfService) {
        this.scheduleService = scheduleService;
        this.pdfService = pdfService;
    }

    @GetMapping    // <- endpoint
    public ResponseEntity<List<ScheduleDto>> getAllSchedules(){
        List<ScheduleDto> list = this.scheduleService.getAllSchedules();
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")   // <- endpoint
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(getSingleScheduleById(id), HttpStatus.OK);
    }

    @GetMapping("/departure/{code}")
    public ResponseEntity<List<ScheduleDto>> getScheduleByDepartureAirportCode(@PathVariable String code){
        List<ScheduleDto> list = this.scheduleService.getScheduleByDepartureAirport(code);
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/arrival/{code}")
    public ResponseEntity<List<ScheduleDto>> getScheduleByArrivalAirportCode(@PathVariable String code){
        List<ScheduleDto> list = this.scheduleService.getScheduleByArrivalAirport(code);
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/flight/{flightNumer}")
    public ResponseEntity<ScheduleDto> getScheduleByFlightNumber(@PathVariable String flightNumer){
        ScheduleDto scheduleDto = this.scheduleService.getScheduleByFlightNumber(flightNumer);
        if(scheduleDto == null){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }

    @GetMapping("/pdf/{id}")   // <- endpoint
    public ResponseEntity<byte[]> getPdfScheduleById(@PathVariable Long id) throws IOException {
        ScheduleDto schedule = getSingleScheduleById(id);
        String filename = "schedule-"+ LocalDateTime.now()+".pdf";
        return pdfService.createPdfResponse(schedule, filename);
    }

    @PostMapping   // <- endpoint
    public ResponseEntity<Void> addSchedule(@RequestBody ScheduleDto schedule){
        ScheduleDto scheduleDto = this.scheduleService.getScheduleByFlightNumber(schedule.getFlightNumber());
        if(scheduleDto != null){
            throw new ScheduleAlreadyExistsException();
        }
        checkIfInputDataIsCorrect(schedule);
        this.scheduleService.saveSchedule(schedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDto schedule){
        schedule.setId(id);
        getSingleScheduleById(id);
        checkIfInputDataIsCorrect(schedule);
        this.scheduleService.deleteSchedule(id);
        this.scheduleService.updateSchedule(schedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        getSingleScheduleById(id);
        this.scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ScheduleDto getSingleScheduleById(Long id){
        ScheduleDto scheduleDto = this.scheduleService.getScheduleById(id);
        if(scheduleDto == null){
            throw new ScheduleNotFoundException();
        }
        return scheduleDto;
    }

    private void checkIfInputDataIsCorrect(ScheduleDto schedule){
        if(schedule.getAircraftCode()==null || schedule.getFlightNumber().isEmpty() || schedule.getAirlineCode()==null || schedule.getDuration()==null || schedule.getArrivalDateTime()==null || schedule.getArrivalAirportCode()==null || schedule.getDepartureDateTime()==null || schedule.getDepartureAirportCode()==null || !schedule.getDuration().isPositive()){
            throw new ScheduleWrongInputException();
        }
    }
}
