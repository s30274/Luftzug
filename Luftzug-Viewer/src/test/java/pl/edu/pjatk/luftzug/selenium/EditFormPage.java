package pl.edu.pjatk.luftzug.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditFormPage {
    private WebDriver webDriver;

    @FindBy(id="flightNumber")
    private WebElement flightNumberInput;

    @FindBy(id="duration")
    private WebElement durationInput;

    @FindBy(id="departureAirportCode")
    private WebElement departureAirportCodeInput;

    @FindBy(id="departureDateTime")
    private WebElement departureDateTimeInput;

    @FindBy(id="arrivalAirportCode")
    private WebElement arrivalAirportCodeInput;

    @FindBy(id="arrivalDateTime")
    private WebElement arrivalDateTimeInput;

    @FindBy(id="airlineCode")
    private WebElement airlineCodeInput;

    @FindBy(id="aircraftCode")
    private WebElement aircraftCodeInput;

    @FindBy(id="submit")
    private WebElement submitButton;

    public EditFormPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public EditFormPage open(String id) {
        this.webDriver.get("http://localhost:8082/editForm/"+id);
        return this;
    }

    public EditFormPage fillInFlightNumberInput(String text){
        this.flightNumberInput.clear();
        this.flightNumberInput.sendKeys(text);
        return this;
    }

    public EditFormPage fillInDurationInput(String test){
        this.durationInput.clear();
        this.durationInput.sendKeys(test);
        return this;
    }

    public EditFormPage fillInDepartureAirportCodeInput(String text){
        this.departureAirportCodeInput.clear();
        this.departureAirportCodeInput.sendKeys(text);
        return this;
    }

    public EditFormPage fillInDepartureDateTimeInput(LocalDateTime date){
        this.departureDateTimeInput.clear();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = date.format(myFormatObj);
        this.departureDateTimeInput.sendKeys(formattedDate);
        return this;
    }

    public EditFormPage fillInArrivalAirportCodeInput(String text){
        this.arrivalAirportCodeInput.clear();
        this.arrivalAirportCodeInput.sendKeys(text);
        return this;
    }

    public EditFormPage fillInArrivalDateTimeInput(LocalDateTime date){
        this.arrivalDateTimeInput.clear();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = date.format(myFormatObj);
        this.arrivalDateTimeInput.sendKeys(formattedDate);
        return this;
    }

    public EditFormPage fillInAirlineCodeInput(String text){
        this.airlineCodeInput.clear();
        this.airlineCodeInput.sendKeys(text);
        return this;
    }

    public EditFormPage fillInAircraftCodeInput(String text){
        this.aircraftCodeInput.clear();
        this.aircraftCodeInput.sendKeys(text);
        return this;
    }

    public ViewAllPage submitForm() {
        this.submitButton.click();
        return new ViewAllPage(this.webDriver);
    }
}