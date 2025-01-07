package pl.edu.pjatk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pjatk.client.IScheduleClient;
import pl.edu.pjatk.client.ScheduleClient;
import pl.edu.pjatk.client.contract.AirportDto;
import pl.edu.pjatk.client.model.*;

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
//        List<Aircraft> aircrafts = client.getAllAircrafts();
//        List<Airline> airlines = client.getAllAirlines();
//        List<Airport> airports = client.getAllAirports();
//        List<Country> countries = client.getAllCountries();
        List<Schedule> schedules = client.getAllSchedules("WAW", "ZRH", LocalDate.now());
    }
}
