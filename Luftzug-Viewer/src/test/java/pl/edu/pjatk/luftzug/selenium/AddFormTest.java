package pl.edu.pjatk.luftzug.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFormTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        this.webDriver = new FirefoxDriver();
//        this.webDriver = new ChromeDriver();
//        this.webDriver = new EdgeDriver();
    }

    @Test
    public void testAddForm(){
        AddFormPage addFormPage = new AddFormPage(webDriver);
        addFormPage.open()
                .fillInFlightNumberInput("4200")
                .fillInDurationInput("PT4H20M")
                .fillInDepartureAirportCodeInput("WAW")
                .fillInDepartureDateTimeInput("2024-03-21T04:20:00")
                .fillInArrivalAirportCodeInput("WAW")
                .fillInArrivalDateTimeInput("2024-03-21T07:20:00")
                .fillInAirlineCodeInput("LX")
                .fillInAircraftCodeInput("320");
        ViewAllPage viewAllPage = addFormPage.submitForm();

        assertTrue(viewAllPage.getData().contains("4200 PT4H20M WAW 2024-03-21T04:20:00 WAW 2024-03-21T07:20:00 LX 320"));
    }

}