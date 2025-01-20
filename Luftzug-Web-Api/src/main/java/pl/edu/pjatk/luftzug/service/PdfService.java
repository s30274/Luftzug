package pl.edu.pjatk.luftzug.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.model.Schedule;
import pl.edu.pjatk.luftzug.service.abstraction.IPdfService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Random;

@Service
public class PdfService implements IPdfService {
    public ResponseEntity<byte[]> createPdfResponse(ScheduleDto schedule, String filename) throws IOException {
        byte[] contents = generatePdfContents(schedule);
        HttpHeaders headers = getHttpHeadersForPdf(filename);
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        headers.setContentDispositionFormData(filename, filename);
        return response;
    }

    private byte[] generatePdfContents(ScheduleDto schedule) throws IOException {
        File file = generateTemporaryPdfFile(schedule);
        byte[] contents = Files.readAllBytes(file.toPath());
        file.delete();
        return contents;
    }

    private File generateTemporaryPdfFile(ScheduleDto schedule) throws IOException {
        PDDocument document = createDocument(schedule);
        File file = new File(new Random().toString());
        document.save(file);
        document.close();
        return file;
    }

    private PDDocument createDocument(ScheduleDto schedule) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        fillPage(schedule, new PDPageContentStream(document, page));
        document.addPage(page);
        return document;
    }

    private void fillPage(ScheduleDto schedule, PDPageContentStream stream) throws IOException {
        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 14);
        stream.newLineAtOffset(25, 600);    // Set cursor to initial position
        stream.showText("Flight number: " + schedule.getFlightNumber());
        stream.newLineAtOffset(0, -50);
        stream.showText("Departure airport: " + schedule.getDepartureAirportCode());
        stream.newLineAtOffset(0, -50);
        stream.showText("Departure datetime: " + schedule.getDepartureDateTime().toString());
        stream.newLineAtOffset(0, -50);
        stream.showText("Arrival airport: " + schedule.getArrivalAirportCode());
        stream.newLineAtOffset(0, -50);
        stream.showText("Arrival datetime: " + schedule.getArrivalDateTime().toString());
        stream.endText();
        stream.close();
    }

    private HttpHeaders getHttpHeadersForPdf(String filename){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        return headers;
    }
}
