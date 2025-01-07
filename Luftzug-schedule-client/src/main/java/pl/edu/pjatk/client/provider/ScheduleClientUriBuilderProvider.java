package pl.edu.pjatk.client.provider;

import org.springframework.beans.factory.annotation.Value;

public record ScheduleClientUriBuilderProvider(String host, int version) implements IScheduleClientUriBuilderProvider{

    public ScheduleClientUriBuilderProvider(
            @Value("${api.host}") String host,
            @Value("${api.version}") int version){

        this.host = host;
        this.version = version;
    }
}
