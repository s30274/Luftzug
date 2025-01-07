package pl.edu.pjatk.client.provider;

import org.springframework.web.util.UriComponentsBuilder;

public interface IScheduleClientUriBuilderProvider {
    String host();
    int version();
    default UriComponentsBuilder builder(){
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(host())
                .pathSegment("v"+version());
    }
}
