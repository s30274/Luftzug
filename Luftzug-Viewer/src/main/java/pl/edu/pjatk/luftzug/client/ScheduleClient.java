package pl.edu.pjatk.luftzug.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import pl.edu.pjatk.luftzug.client.provider.IScheduleClientUriBuilderProvider;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;

import java.util.List;

public class ScheduleClient {
    private final IScheduleClientUriBuilderProvider provider;
    private final RestClient restClient;

    public ScheduleClient(IScheduleClientUriBuilderProvider provider){
        this.restClient = RestClient.create();
        this.provider = provider;
    }

    public List<ScheduleDto> getAllSchedules(){
        String uri = provider.builder()
                .pathSegment("schedule")
                .toUriString();
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ScheduleDto>>() {});
    }

    public ScheduleDto getScheduleById(Long id){
        String uri = provider.builder()
                .pathSegment("schedule", id+"")
                .toUriString();
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(ScheduleDto.class);
    }

    public void addSchedule(ScheduleDto scheduleDto){
        String uri = provider.builder()
                .pathSegment("schedule")
                .toUriString();
        restClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(scheduleDto)
                .retrieve()
                .toBodilessEntity();
    }

    public void updateSchedule(ScheduleDto scheduleDto){
        String uri = provider.builder()
                .pathSegment("schedule", scheduleDto.getId()+"")
                .toUriString();
        restClient.put()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(scheduleDto)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteSchedule(Long id){
        String uri = provider.builder()
                .pathSegment("schedule", id+"")
                .toUriString();
        restClient.delete()
                .uri(uri)
                .retrieve()
                .toBodilessEntity();
    }
}
