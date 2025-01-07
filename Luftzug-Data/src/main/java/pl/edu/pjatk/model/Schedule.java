package pl.edu.pjatk.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private Duration duration;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arriavalAirport;
    @ManyToOne
    private Airline airline;
    @ManyToOne
    private Aircraft aircraft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber(){return flightNumber;}

    public void setFlightNumber(String flightNumber){this.flightNumber = flightNumber;}

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArriavalAirport() {
        return arriavalAirport;
    }

    public void setArriavalAirport(Airport arriavalAirport) {
        this.arriavalAirport = arriavalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(duration, schedule.duration) && Objects.equals(departureDateTime, schedule.departureDateTime) && Objects.equals(arrivalDateTime, schedule.arrivalDateTime) && Objects.equals(departureAirport, schedule.departureAirport) && Objects.equals(arriavalAirport, schedule.arriavalAirport) && Objects.equals(airline, schedule.airline) && Objects.equals(aircraft, schedule.aircraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, departureDateTime, arrivalDateTime, departureAirport, arriavalAirport, airline, aircraft);
    }
}
