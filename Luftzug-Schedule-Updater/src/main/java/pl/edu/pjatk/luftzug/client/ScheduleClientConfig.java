package pl.edu.pjatk.luftzug.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.luftzug.client.provider.IScheduleClientUriBuilderProvider;
import pl.edu.pjatk.luftzug.client.provider.ScheduleClientUriBuilderProvider;

@Configuration
public class ScheduleClientConfig {

    @Bean
    public IScheduleClientUriBuilderProvider scheduleClientUriBuilderProvider(@Value("${api.host}") String host,
                                                                              @Value("${api.version}") int version){
        return new ScheduleClientUriBuilderProvider(host, version);
    }

    @Bean
    public ScheduleClient scheduleClient(IScheduleClientUriBuilderProvider uriBuilderProvider, @Value("${api.token}") String token){
        return new ScheduleClient(uriBuilderProvider, token);
    }
}
