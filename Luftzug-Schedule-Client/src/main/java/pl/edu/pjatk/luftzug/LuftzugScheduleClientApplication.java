package pl.edu.pjatk.luftzug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pjatk.luftzug.client.IScheduleClient;
import pl.edu.pjatk.luftzug.client.ScheduleClient;
import pl.edu.pjatk.luftzug.client.contract.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class LuftzugScheduleClientApplication implements CommandLineRunner {
    private final IScheduleClient client;

    public LuftzugScheduleClientApplication(ScheduleClient scheduleClient){
        this.client = scheduleClient;
    }

    public static void main(String[] args){
        SpringApplication.run(LuftzugScheduleClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<AircraftDto> aircrafts = client.getAllAircrafts();
//        List<AirlineDto> airlines = client.getAllAirlines();
//        List<AirportDto> airports = client.getAllAirports();
//        List<CountryDto> countries = client.getAllCountries();
//        List<ScheduleDto> schedules = client.getAllSchedules("WAW", "ZRH", LocalDate.now());
    }
}
