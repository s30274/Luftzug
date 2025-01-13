package pl.edu.pjatk.luftzug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.edu.pjatk.luftzug.updater.IUpdateSchedules;

import java.time.LocalDate;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "pl.edu.pjatk.luftzug")
public class LuftzugUpdaterApplication implements CommandLineRunner {
    private final IUpdateSchedules updater;

    public LuftzugUpdaterApplication(IUpdateSchedules updater){this.updater = updater;}

    public static void main(String[] args) {
        SpringApplication.run(LuftzugUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        updater.updateAll("WAW", "ZRH", LocalDate.now());
        updater.updateSchedulesOnly("ZRH", "WAW", LocalDate.now());
        updater.updateSchedulesOnly("CTA", "ZRH", LocalDate.now());
        updater.updateSchedulesOnly("ZRH", "CTA", LocalDate.now());
        updater.updateSchedulesOnly("ZDH", "ZRH", LocalDate.now());
        updater.updateSchedulesOnly("ZRH", "ZDH", LocalDate.now());
        updater.updateSchedulesOnly("WAW", "MUC", LocalDate.now());
        updater.updateSchedulesOnly("MUC", "ZDH", LocalDate.now());
    }
}
