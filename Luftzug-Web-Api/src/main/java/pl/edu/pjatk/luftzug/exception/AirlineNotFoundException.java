package pl.edu.pjatk.luftzug.exception;

public class AirlineNotFoundException extends RuntimeException {

    public AirlineNotFoundException(){super("Airline not found!");}
}
