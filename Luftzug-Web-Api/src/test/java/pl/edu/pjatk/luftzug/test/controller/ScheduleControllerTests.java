package pl.edu.pjatk.luftzug.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.edu.pjatk.luftzug.contract.ScheduleDto;
import pl.edu.pjatk.luftzug.controller.ScheduleController;
import pl.edu.pjatk.luftzug.model.*;
import pl.edu.pjatk.luftzug.service.PdfService;
import pl.edu.pjatk.luftzug.service.ScheduleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScheduleService scheduleService;

    @MockitoBean
    private PdfService pdfService;

    @Autowired
    private ObjectMapper objectMapper;

    private Schedule schedule;

    private ScheduleDto scheduleDto;

    @BeforeEach
    public void setup(){
        schedule = getFirstSchedule();
        scheduleDto = new ScheduleDto(1L, "2137", Duration.parse("PT1H37M"), "WAW", LocalDateTime.parse("2025-01-20T20:00"), "ZRH", LocalDateTime.parse("2025-01-20T21:37"), "LX", "320");
    }

    @Test
    @Order(1)
    public void saveSchedule() throws Exception {

        // precondition
        given(scheduleService.saveSchedule(any(ScheduleDto.class))).willReturn(schedule);

        // action
        ResultActions response = mockMvc.perform(post("/api/v1/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scheduleDto)));

        // verify
        response.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void getAllSchedules() throws Exception {
        ScheduleDto scheduleDto1 = new ScheduleDto(2L, "2138", Duration.parse("PT1H37M"), "ZRH", LocalDateTime.parse("2025-01-20T16:00"), "WAW", LocalDateTime.parse("2025-01-20T17:37"), "LX", "320");

        // precondition
        given(scheduleService.getAllSchedules()).willReturn(List.of(scheduleDto, scheduleDto1));

        // action
        ResultActions response = mockMvc.perform(get("/api/v1/schedule"));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(2)));
    }

    @Test
    @Order(3)
    public void getScheduleById() throws Exception {

        // precondition
        given(scheduleService.getScheduleById(schedule.getId())).willReturn(scheduleDto);

        // action
        ResultActions response = mockMvc.perform(get("/api/v1/schedule/{id}", scheduleDto.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.flightNumber", is(scheduleDto.getFlightNumber())))
                .andExpect(jsonPath("$.duration", is(scheduleDto.getDuration().toString())))
                .andExpect(jsonPath("$.departureAirportCode", is(scheduleDto.getDepartureAirportCode())))
                .andExpect(jsonPath("$.departureDateTime", is(scheduleDto.getDepartureDateTime().toString()+":00")))
                .andExpect(jsonPath("$.arrivalAirportCode", is(scheduleDto.getArrivalAirportCode())))
                .andExpect(jsonPath("$.arrivalDateTime", is(scheduleDto.getArrivalDateTime().toString()+":00")))
                .andExpect(jsonPath("$.airlineCode", is(scheduleDto.getAirlineCode())))
                .andExpect(jsonPath("$.aircraftCode", is(scheduleDto.getAircraftCode())));
    }

    @Test
    @Order(4)
    public void getScheduleByFlightNumber() throws Exception {

        // precondition
        given(scheduleService.getScheduleByFlightNumber(schedule.getFlightNumber())).willReturn(scheduleDto);

        // action
        ResultActions response = mockMvc.perform(get("/api/v1/schedule/flight/{flightNumber}", scheduleDto.getFlightNumber()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.flightNumber", is(scheduleDto.getFlightNumber())))
                .andExpect(jsonPath("$.duration", is(scheduleDto.getDuration().toString())))
                .andExpect(jsonPath("$.departureAirportCode", is(scheduleDto.getDepartureAirportCode())))
                .andExpect(jsonPath("$.departureDateTime", is(scheduleDto.getDepartureDateTime().toString()+":00")))
                .andExpect(jsonPath("$.arrivalAirportCode", is(scheduleDto.getArrivalAirportCode())))
                .andExpect(jsonPath("$.arrivalDateTime", is(scheduleDto.getArrivalDateTime().toString()+":00")))
                .andExpect(jsonPath("$.airlineCode", is(scheduleDto.getAirlineCode())))
                .andExpect(jsonPath("$.aircraftCode", is(scheduleDto.getAircraftCode())));
    }

    @Test
    @Order(5)
    public void updateSchedule() throws Exception{

        // precondition
        given(scheduleService.getScheduleById(schedule.getId())).willReturn(scheduleDto);
        schedule.setFlightNumber("2138");
        scheduleDto.setFlightNumber("2138");
        given(scheduleService.updateSchedule(scheduleDto)).willReturn(schedule);

        // action
        ResultActions response = mockMvc.perform(put("/api/v1/schedule/{id}", scheduleDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scheduleDto)));

        // verify
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void deleteSchedule() throws Exception{

        // prediciton
        given(scheduleService.getScheduleById(schedule.getId())).willReturn(scheduleDto);
        willDoNothing().given(scheduleService).deleteSchedule(schedule.getId());

        // action
        ResultActions response = mockMvc.perform(delete("/api/v1/schedule/{id}", schedule.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print());
    }

    private Schedule getFirstSchedule(){
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setCode("320");
        aircraft.setName("Airbus A320");

        Airline airline = new Airline();
        airline.setId(1L);
        airline.setCode("LX");
        airline.setName("Swiss");

        Country departureCountry = new Country();
        departureCountry.setId(1);
        departureCountry.setCode("PL");
        departureCountry.setName("Poland");

        Country arrivalCountry = new Country();
        arrivalCountry.setId(2);
        arrivalCountry.setCode("CH");
        arrivalCountry.setName("Switzerland");

        Airport departureAirport = new Airport();
        departureAirport.setId(1L);
        departureAirport.setCode("WAW");
        departureAirport.setName("Warsaw Chopin Airport");
        departureAirport.setLongitude(52);
        departureAirport.setLatitude(20);
        departureAirport.setCountry(departureCountry);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setId(2L);
        arrivalAirport.setCode("ZRH");
        arrivalAirport.setName("Zurich Airport");
        arrivalAirport.setLongitude(21);
        arrivalAirport.setLatitude(37);
        arrivalAirport.setCountry(arrivalCountry);

        Schedule schedule1 = new Schedule();
        schedule1.setId(1L);
        schedule1.setDuration(Duration.parse("PT1H37M"));
        schedule1.setDepartureAirport(departureAirport);
        schedule1.setDepartureDateTime(LocalDateTime.parse("2025-01-20T20:00"));
        schedule1.setArrivalAirport(arrivalAirport);
        schedule1.setArrivalDateTime(LocalDateTime.parse("2025-01-20T21:37"));
        schedule1.setAirline(airline);
        schedule1.setFlightNumber("2137");
        schedule1.setAircraft(aircraft);

        return schedule1;
    }
}
