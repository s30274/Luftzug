package pl.edu.pjatk.luftzug.client.provider;

import org.springframework.beans.factory.annotation.Value;

public record ScheduleClientUriBuilderProvider(String host, int port, int version) implements IScheduleClientUriBuilderProvider{

    public ScheduleClientUriBuilderProvider(
            @Value("${api.host}") String host,
            @Value("${api.port}") int port,
            @Value("${api.version}") int version) {

        this.host = host;
        this.port = port;
        this.version = version;
    }
}