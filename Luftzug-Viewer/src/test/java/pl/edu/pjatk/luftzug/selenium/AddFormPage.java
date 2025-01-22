package pl.edu.pjatk.luftzug.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
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

    public AddFormPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddFormPage open() {
        this.webDriver.get("http://localhost:8082/addForm");
        return this;
    }

    public AddFormPage fillInFlightNumberInput(String text){
        this.flightNumberInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInDurationInput(String text){
        this.durationInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInDepartureAirportCodeInput(String text){
        this.departureAirportCodeInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInDepartureDateTimeInput(String text){
        this.departureDateTimeInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInArrivalAirportCodeInput(String text){
        this.arrivalAirportCodeInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInArrivalDateTimeInput(String text){
        this.arrivalDateTimeInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInAirlineCodeInput(String text){
        this.airlineCodeInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInAircraftCodeInput(String text){
        this.aircraftCodeInput.sendKeys(text);
        return this;
    }

    public ViewAllPage submitForm() {
        this.submitButton.click();
        return new ViewAllPage(this.webDriver);
    }
}