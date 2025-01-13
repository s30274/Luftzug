package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public record ScheduleDto(
        @JsonProperty("TotalJourney") TotalJourney totalJourney,
        @JsonProperty("Flight") Flight flight) {

    public record TotalJourney(@JsonProperty("Duration") Duration duration){}
    public record Flight(@JsonProperty("Departure") Departure departure,
                         @JsonProperty("Arrival") Arrival arrival,
                         @JsonProperty("MarketingCarrier") MarketingCarrier marketingCarrier,
                         @JsonProperty("Equipment") Equipment equipment){
        public record Departure(@JsonProperty("AirportCode") String code,
                                @JsonProperty("ScheduledTimeLocal") ScheduledTimeLocal scheduledTimeLocal){
            public record ScheduledTimeLocal(@JsonProperty("DateTime") LocalDateTime localDateTime){}
        }
        public record Arrival(@JsonProperty("AirportCode") String code,
                                @JsonProperty("ScheduledTimeLocal") ScheduledTimeLocal scheduledTimeLocal){
            public record ScheduledTimeLocal(@JsonProperty("DateTime") LocalDateTime localDateTime){}
        }
        public record MarketingCarrier(@JsonProperty("AirlineID") String code,
                                       @JsonProperty("FlightNumber") String flightNumber){}
        public record Equipment(@JsonProperty("AircraftCode") String code){}
    }
}