package pl.edu.pjatk.luftzug.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewerTests {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        this.webDriver = new FirefoxDriver();
//        this.webDriver = new ChromeDriver();
//        this.webDriver = new EdgeDriver();
    }

    @Test
    @Order(1)
    public void testAddForm(){
        AddFormPage addFormPage = new AddFormPage(webDriver);
        addFormPage.open()
                .fillInFlightNumberInput("4200")
                .fillInDurationInput("PT4H20M")
                .fillInDepartureAirportCodeInput("WAW")
                .fillInDepartureDateTimeInput(LocalDateTime.parse("2024-03-21T04:20:00"))
                .fillInArrivalAirportCodeInput("WAW")
                .fillInArrivalDateTimeInput(LocalDateTime.parse("2024-03-21T07:20:00"))
                .fillInAirlineCodeInput("LX")
                .fillInAircraftCodeInput("320");
        ViewAllPage viewAllPage = addFormPage.submitForm();

        assertTrue(viewAllPage.getData().contains("4200 PT4H20M WAW 2024-03-21T04:20 WAW 2024-03-21T07:20 LX 320"));
    }

    @Test
    @Order(2)
    public void testEditForm(){
        ViewAllPage viewAllPage = new ViewAllPage(webDriver);
        viewAllPage.open();
        EditFormPage editFormPage = viewAllPage.editLastSchedule();
        editFormPage
                .fillInFlightNumberInput("4201")
                .fillInDepartureDateTimeInput(LocalDateTime.parse("2024-03-21T04:20:00"))
                .fillInArrivalDateTimeInput(LocalDateTime.parse("2024-03-21T07:20:00"));
        ViewAllPage viewAllPage1 = editFormPage.submitForm();

        assertTrue(viewAllPage1.getData().contains("4201 PT4H20M WAW 2024-03-21T04:20 WAW 2024-03-21T07:20 LX 320"));
    }

    @Test
    @Order(3)
    public void testDelete(){
        ViewAllPage viewAllPage = new ViewAllPage(webDriver);
        viewAllPage.open();
        ViewAllPage viewAllPage1 = viewAllPage.deleteLastSchedule();

        assertFalse(viewAllPage1.getData().contains("4201 PT4H20M WAW 2024-03-21T04:20 WAW 2024-03-21T07:20 LX 320"));
    }
}