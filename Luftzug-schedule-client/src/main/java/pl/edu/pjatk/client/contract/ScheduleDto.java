package pl.edu.pjatk.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.client.model.Schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record ScheduleDto(@JsonProperty("ScheduleResource") ScheduleResource scheduleResource){
    public record ScheduleResource(@JsonProperty("Schedule") List<Schedule> scheduleList){}
}
