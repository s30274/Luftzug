package pl.edu.pjatk.luftzug.exception;

public class ScheduleAlreadyExistsException extends RuntimeException {
    public ScheduleAlreadyExistsException(){super("Schedule entry already exists!");}
}
