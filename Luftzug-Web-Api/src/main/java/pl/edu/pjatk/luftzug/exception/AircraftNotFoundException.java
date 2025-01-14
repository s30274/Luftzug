package pl.edu.pjatk.luftzug.exception;

public class AircraftNotFoundException extends RuntimeException {

    public AircraftNotFoundException(){super("Aircraft not found!");}
}
