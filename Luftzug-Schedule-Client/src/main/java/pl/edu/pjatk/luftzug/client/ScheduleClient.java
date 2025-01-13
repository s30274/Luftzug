package pl.edu.pjatk.luftzug.client;

import org.springframework.web.client.RestClient;
import pl.edu.pjatk.luftzug.client.contract.*;
import pl.edu.pjatk.luftzug.client.contract.root.*;
import pl.edu.pjatk.luftzug.client.provider.IScheduleClientUriBuilderProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleClient implements IScheduleClient {

    private RestClient restClient;
    private IScheduleClientUriBuilderProvider provider;
    private String token;
    private int pageSize;

    public ScheduleClient(IScheduleClientUriBuilderProvider provider, String token){
        this.restClient = RestClient.create();
        this.provider = provider;
        this.token = token;
        this.pageSize = 100;
    }

    @Override
    public List<AircraftDto> getAllAircrafts() {
        List<AircraftDto> aircraftList = new ArrayList<>();
        AircraftRoot aircraftRoot = getPagedAircraft(1, pageSize);
        aircraftList.addAll(aircraftRoot.aircraftResource().aircrafts().aircraftDtos());

        int pages = getNumberOfPages(aircraftRoot.aircraftResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            aircraftList.addAll(getPagedAircraft(i, pageSize).aircraftResource().aircrafts().aircraftDtos());
        }
        return aircraftList;
    }

    private AircraftRoot getPagedAircraft(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "aircraft");
        wait200ms();             // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AircraftRoot.class);
    }

    @Override
    public List<AirlineDto> getAllAirlines() {
        List<AirlineDto> airlineList = new ArrayList<>();
        AirlineRoot airlineRoot = getPagedAirline(1, pageSize);
        airlineList.addAll(airlineRoot.airlineResource().airlines().airlineDtos());

        int pages = getNumberOfPages(airlineRoot.airlineResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            airlineList.addAll(getPagedAirline(i, pageSize).airlineResource().airlines().airlineDtos());
        }
        return airlineList;
    }

    private AirlineRoot getPagedAirline(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "airlines");
        wait200ms();             // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AirlineRoot.class);
    }

    @Override
    public List<AirportDto> getAllAirports() {
        List<AirportDto> airportList = new ArrayList<>();
        AirportRoot airportRoot = getPagedAirport(1, pageSize);
        airportList.addAll(airportRoot.airportResource().airports().airportDtos());

        int pages = getNumberOfPages(airportRoot.airportResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
             airportList.addAll(getPagedAirport(i, pageSize).airportResource().airports().airportDtos());
        }
        return airportList;
    }

    private AirportRoot getPagedAirport(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "airports");
        wait200ms();             // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AirportRoot.class);
    }

    private void wait200ms(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public List<CountryDto> getAllCountries() {
        List<CountryDto> countryList = new ArrayList<>();
        CountryRoot countryRoot = getPagedCountry(1, pageSize);
        countryList.addAll(countryRoot.countryResource().countries().countryDtos());

        int pages = getNumberOfPages(countryRoot.countryResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            countryList.addAll(getPagedCountry(i, pageSize).countryResource().countries().countryDtos());
        }
        return countryList;
    }

    private CountryRoot getPagedCountry(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "countries");
        wait200ms();             // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(CountryRoot.class);
    }

    @Override
    public List<ScheduleDto> getAllSchedules(String departureAirport, String arrivalAirport, LocalDate date) {
        String uri = getScheduleUri(departureAirport, arrivalAirport, date);
        wait200ms();             // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(ScheduleRoot.class)
                .scheduleResource().scheduleDtos();
    }

    private String getPageUri(int page, int pageSize, String segment){
        return provider.builder()
                .pathSegment("mds-references")
                .pathSegment(segment)
                .queryParam("lang", "EN")
                .queryParam("limit", pageSize)
                .queryParam("offset", (page-1)*pageSize)
                .queryParam("LHoperated", 1)
                .toUriString();
    }

    private String getScheduleUri(String departureAirport, String arrivalAirport, LocalDate date){
        return provider.builder()
                .pathSegment("operations")
                .pathSegment("schedules")
                .pathSegment(departureAirport)
                .pathSegment(arrivalAirport)
                .pathSegment(date.toString())
                .queryParam("directFlights",1)
                .toUriString();
    }

    private int getNumberOfPages(int count, int pageSize){
        return (int) Math.ceil((double) count / (double) pageSize);
    }
}
