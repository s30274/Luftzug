package pl.edu.pjatk.luftzug.exception;

public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(){super("Airport not found!");}
}
