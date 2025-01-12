package pl.edu.pjatk.luftzug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pjatk.luftzug.repository.CountryRepository;

@SpringBootApplication
public class LuftzugDataApplication implements CommandLineRunner {

    private final CountryRepository countryRepository;

    public LuftzugDataApplication(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(LuftzugDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.countryRepository.flush();
    }
}
