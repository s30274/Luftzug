package pl.edu.pjatk.luftzug.service.abstraction;

import org.springframework.http.ResponseEntity;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;

import java.io.IOException;

public interface IPdfService {
    ResponseEntity<byte[]> createPdfResponse(ScheduleDto schedule, String fileName) throws IOException;
}
