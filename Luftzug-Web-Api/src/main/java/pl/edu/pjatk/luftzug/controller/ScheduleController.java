package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        List<Schedule> list = this.scheduleService.getAllSchedules();
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")   // <- endpoint
    public ResponseEntity<Optional<Schedule>> getScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(getSingleScheduleById(id), HttpStatus.OK);
    }

    @GetMapping("/departure/{code}")
    public ResponseEntity<List<Schedule>> getScheduleByDepartureAirportCode(@PathVariable String code){
        List<Schedule> list = this.scheduleService.getScheduleByDepartureAirport(code);
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/arrival/{code}")
    public ResponseEntity<List<Schedule>> getScheduleByArrivalAirportCode(@PathVariable String code){
        List<Schedule> list = this.scheduleService.getScheduleByArrivalAirport(code);
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/flight/{flightNumer}")
    public ResponseEntity<List<Schedule>> getScheduleByFlightNumber(@PathVariable String flightNumer){
        List<Schedule> list = this.scheduleService.getScheduleByFlightNumber(flightNumer);
        if(list.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pdf/{id}")   // <- endpoint
    public ResponseEntity<byte[]> getPdfScheduleById(@PathVariable Long id) throws IOException {
        Optional<Schedule> schedule = getSingleScheduleById(id);
        String filename = "schedule-"+ LocalDateTime.now()+".pdf";
        return pdfService.createPdfResponse(schedule, filename);
    }

    @PostMapping   // <- endpoint
    public ResponseEntity<Void> addSchedule(@RequestBody Schedule schedule){
        List<Schedule> schedules = this.scheduleService.getScheduleByFlightNumber(schedule.getFlightNumber());
        if(!schedules.isEmpty()){
            throw new ScheduleAlreadyExistsException();
        }
        checkIfInputDataIsCorrect(schedule);
        this.scheduleService.saveSchedule(schedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule){
        Schedule scheduleToUpdate = getSingleScheduleById(id).get();
        checkIfInputDataIsCorrect(schedule);
        this.scheduleService.updateSchedule(schedule, scheduleToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        getSingleScheduleById(id);
        this.scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Optional<Schedule> getSingleScheduleById(Long id){
        Optional<Schedule> schedule = this.scheduleService.getScheduleById(id);
        if(schedule.isEmpty()){
            throw new ScheduleNotFoundException();
        }
        return schedule;
    }

    private void checkIfInputDataIsCorrect(Schedule schedule){
        if(schedule.getAircraft()==null || schedule.getFlightNumber().isEmpty() || schedule.getAirline()==null || schedule.getDuration()==null || schedule.getArrivalDateTime()==null || schedule.getArrivalAirport()==null || schedule.getDepartureDateTime()==null || schedule.getDepartureAirport()==null || !schedule.getDuration().isPositive()){
            throw new ScheduleWrongInputException();
        }
    }
}
