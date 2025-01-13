package pl.edu.pjatk.luftzug.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(){super("Schedule entry not found!");}
}
