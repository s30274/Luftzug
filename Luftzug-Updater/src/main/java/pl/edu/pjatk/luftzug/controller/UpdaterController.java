package pl.edu.pjatk.luftzug.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.pjatk.luftzug.updater.IUpdateSchedules;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("updater")
public class UpdaterController {
    private final IUpdateSchedules updater;

    public UpdaterController(IUpdateSchedules updater) {
        this.updater = updater;
    }

    @GetMapping("start")
    public ResponseEntity start(@RequestParam String departureAirport, @RequestParam String arrivalAirport, @RequestParam LocalDate date){
        updater.updateSchedulesOnly(departureAirport, arrivalAirport, date);

        return ResponseEntity.ok("Update started on " + LocalDateTime.now());
    }
}
