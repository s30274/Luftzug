package pl.edu.pjatk.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.client.model.Country;
import pl.edu.pjatk.client.model.Meta;

import java.util.List;

public record CountryDto(@JsonProperty("CountryResource") CountryResource countryResource){
    public record CountryResource(@JsonProperty("Countries") Countries countries,
                                  @JsonProperty("Meta") Meta meta){
        public record Countries(@JsonProperty("Country") List<Country> countryList){}
    }
}
