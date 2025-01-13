package pl.edu.pjatk.luftzug.service;

import org.springframework.http.ResponseEntity;
import pl.edu.pjatk.luftzug.model.Schedule;

import java.io.IOException;
import java.util.Optional;

public interface IPdfService {
    ResponseEntity<byte[]> createPdfResponse(Optional<Schedule> schedule, String fileName) throws IOException;
}
