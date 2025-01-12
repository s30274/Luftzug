package pl.edu.pjatk.luftzug.client.contract.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.luftzug.client.contract.ScheduleDto;

import java.util.List;

public record ScheduleRoot(@JsonProperty("ScheduleResource") ScheduleResource scheduleResource){
    public record ScheduleResource(@JsonProperty("Schedule") List<ScheduleDto> scheduleDtos){}
}
