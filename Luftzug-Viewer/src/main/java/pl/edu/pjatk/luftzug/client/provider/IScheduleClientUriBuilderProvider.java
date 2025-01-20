package pl.edu.pjatk.luftzug.client.provider;

import org.springframework.web.util.UriComponentsBuilder;

public interface IScheduleClientUriBuilderProvider {
    String host();
    int port();
    int version();
    default UriComponentsBuilder builder(){
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host())
                .port(port())
                .pathSegment("api", "v"+version());
    }
}