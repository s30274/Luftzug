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
        AircraftRoot aircraftRoot = getPagedRoot(1, pageSize, AircraftRoot.class);
        List<AircraftDto> aircraftList = new ArrayList<>(aircraftRoot.aircraftResource().aircrafts().aircraftDtos());

        int pages = getNumberOfPages(aircraftRoot.aircraftResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            aircraftList.addAll((getPagedRoot(i, pageSize, AircraftRoot.class)).aircraftResource().aircrafts().aircraftDtos());
        }
        return aircraftList;
    }
    @Override
    public List<AirlineDto> getAllAirlines() {
        AirlinesRoot airlinesRoot = getPagedRoot(1, pageSize, AirlinesRoot.class);
        List<AirlineDto> airlineList = new ArrayList<>(airlinesRoot.airlineResource().airlines().airlineDtos());

        int pages = getNumberOfPages(airlinesRoot.airlineResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            airlineList.addAll(getPagedRoot(i, pageSize, AirlinesRoot.class).airlineResource().airlines().airlineDtos());
        }
        return airlineList;
    }

    @Override
    public List<AirportDto> getAllAirports() {
        AirportsRoot airportsRoot = getPagedRoot(1, pageSize, AirportsRoot.class);
        List<AirportDto> airportList = new ArrayList<>(airportsRoot.airportResource().airports().airportDtos());

        int pages = getNumberOfPages(airportsRoot.airportResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
             airportList.addAll(getPagedRoot(i, pageSize, AirportsRoot.class).airportResource().airports().airportDtos());
        }
        return airportList;
    }

    @Override
    public List<CountryDto> getAllCountries() {
        CountriesRoot countriesRoot = getPagedRoot(1, pageSize, CountriesRoot.class);
        List<CountryDto> countryList = new ArrayList<>(countriesRoot.countryResource().countries().countryDtos());

        int pages = getNumberOfPages(countriesRoot.countryResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            countryList.addAll(getPagedRoot(i, pageSize, CountriesRoot.class).countryResource().countries().countryDtos());
        }
        return countryList;
    }

    public <T extends IRoot> T getPagedRoot(int page, int pageSize, Class<T> clazz){
        String uri = getPageUri(page, pageSize, clazz.getSimpleName().toLowerCase().substring(0, clazz.getSimpleName().length()-4));    // Get class name without the 'Root' postfix
        wait200ms();    // Wait 200ms so queries per second limit won't be exceeded
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(clazz);
    }

    private int getNumberOfPages(int count, int pageSize){
        return (int) Math.ceil((double) count / (double) pageSize);
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

    private void wait200ms(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
