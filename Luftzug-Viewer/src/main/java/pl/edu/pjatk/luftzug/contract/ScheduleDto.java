package pl.edu.pjatk.luftzug.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long id;
    private String flightNumber;
    private Duration duration;
    private String departureAirportCode;
    private LocalDateTime departureDateTime;
    private String arrivalAirportCode;
    private LocalDateTime arrivalDateTime;
    private String airlineCode;
    private String aircraftCode;
}
