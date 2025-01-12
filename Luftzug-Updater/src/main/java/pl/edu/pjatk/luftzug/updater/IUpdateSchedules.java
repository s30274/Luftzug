package pl.edu.pjatk.luftzug.updater;

import java.time.LocalDate;

public interface IUpdateSchedules {
    void updateAll(String departureAirport, String arrivalAirport, LocalDate date);
    void updateSchedulesOnly(String departureAirport, String arrivalAirport, LocalDate date);
}
