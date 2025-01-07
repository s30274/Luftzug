package pl.edu.pjatk.client;

import org.springframework.web.client.RestClient;
import pl.edu.pjatk.client.contract.*;
import pl.edu.pjatk.client.model.*;
import pl.edu.pjatk.client.provider.IScheduleClientUriBuilderProvider;

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
    public List<Aircraft> getAllAircrafts() {
        List<Aircraft> aircraftList = new ArrayList<>();
        AircraftDto aircraftDto = getPagedAircraft(1, pageSize);
        aircraftList.addAll(aircraftDto.aircraftResource().aircrafts().aircraftList());

        int pages = getNumberOfPages(aircraftDto.aircraftResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            aircraftList.addAll(getPagedAircraft(i, pageSize).aircraftResource().aircrafts().aircraftList());
        }
        return aircraftList;
    }

    private AircraftDto getPagedAircraft(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "aircraft");
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AircraftDto.class);
    }

    @Override
    public List<Airline> getAllAirlines() {
        List<Airline> airlineList = new ArrayList<>();
        AirlineDto airlineDto = getPagedAirline(1, pageSize);
        airlineList.addAll(airlineDto.airlineResource().airlines().airlineList());

        int pages = getNumberOfPages(airlineDto.airlineResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            airlineList.addAll(getPagedAirline(i, pageSize).airlineResource().airlines().airlineList());
        }
        return airlineList;
    }

    private AirlineDto getPagedAirline(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "airlines");
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AirlineDto.class);
    }

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> airportList = new ArrayList<>();
        AirportDto airportDto = getPagedAirport(1, pageSize);
        airportList.addAll(airportDto.airportResource().airports().airportList());

        int pages = getNumberOfPages(airportDto.airportResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
             airportList.addAll(getPagedAirport(i, pageSize).airportResource().airports().airportList());
        }
        return airportList;
    }

    private AirportDto getPagedAirport(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "airports");
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(AirportDto.class);
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countryList = new ArrayList<>();
        CountryDto countryDto = getPagedCountry(1, pageSize);
        countryList.addAll(countryDto.countryResource().countries().countryList());

        int pages = getNumberOfPages(countryDto.countryResource().meta().count(), pageSize);
        for(int i=2; i<=pages; i++){
            countryList.addAll(getPagedCountry(i, pageSize).countryResource().countries().countryList());
        }
        return countryList;
    }

    private CountryDto getPagedCountry(int page, int pagesize){
        String uri = getPageUri(page, pagesize, "countries");
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(CountryDto.class);
    }

    @Override
    public List<Schedule> getAllSchedules(String departureAirport, String arrivalAirport, LocalDate date) {
        String uri = getScheduleUri(departureAirport, arrivalAirport, date);
        System.out.println(uri);
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .body(ScheduleDto.class)
                .scheduleResource().scheduleList();
    }

    private String getPageUri(int page, int pageSize, String segment){
        return provider.builder()
                .pathSegment("mds-references")
                .pathSegment(segment)
                .queryParam("limit", pageSize)
                .queryParam("offset", (page-1)*pageSize)
                .queryParam("LHoperated", 0)
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
//        return (int) Math.ceil((double) count / (double) pageSize);
        return 1;
    }
}
