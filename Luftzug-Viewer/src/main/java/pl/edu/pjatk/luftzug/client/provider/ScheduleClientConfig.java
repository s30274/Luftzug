package pl.edu.pjatk.luftzug.client.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.luftzug.client.ScheduleClient;

@Configuration
public class ScheduleClientConfig {

    @Bean
    public IScheduleClientUriBuilderProvider provider(@Value("${api.host}") String host,
                                                      @Value("${api.port}") int port,
                                                      @Value("${api.version}") int version){
        return new ScheduleClientUriBuilderProvider(host, port, version);
    }

    @Bean
    public ScheduleClient scheduleClient(IScheduleClientUriBuilderProvider provider){
        return new ScheduleClient(provider);
    }
}
