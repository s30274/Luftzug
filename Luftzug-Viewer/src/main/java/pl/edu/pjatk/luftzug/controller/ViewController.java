package pl.edu.pjatk.luftzug.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.luftzug.client.ScheduleClient;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ScheduleClient scheduleClient;

    @GetMapping("view/all")
    public String viewAllSchedules(Model model){
        List<ScheduleDto> scheduleList = scheduleClient.getAllSchedules();
        model.addAttribute("scheduleList", scheduleList);
        return "viewAll";
    }

    @GetMapping("addForm")
    public String displayAddForm(Model model){
        model.addAttribute("schedule", new ScheduleDto(null, "", Duration.ofSeconds(3661), "", LocalDateTime.now(), "", LocalDateTime.now(), "", ""));
        return "addForm";
    }

    @PostMapping("addForm")
    public String displayAddForm(@ModelAttribute ScheduleDto scheduleDto){
        this.scheduleClient.addSchedule(scheduleDto);
        return "redirect:/view/all";
    }

    @GetMapping("editForm/{id}")
    public String displayUpdateForm(Model model, @PathVariable Long id){
        model.addAttribute("schedule", scheduleClient.getScheduleById(id));
        return "editForm";
    }

    @PostMapping("editForm/{id}")
    public String displayUpdateForm(@PathVariable Long id, @ModelAttribute ScheduleDto scheduleDto){
        this.scheduleClient.updateSchedule(scheduleDto);
        return "redirect:/view/all";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        this.scheduleClient.deleteSchedule(id);
        return "redirect:/view/all";
    }
}
