package pl.edu.pjatk.luftzug.client.contract.root;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pjatk.luftzug.client.contract.CountryDto;
import pl.edu.pjatk.luftzug.client.contract.Meta;

import java.util.List;

public record CountryRoot(@JsonProperty("CountryResource") CountryResource countryResource){
    public record CountryResource(@JsonProperty("Countries") Countries countries,
                                  @JsonProperty("Meta") Meta meta){
        public record Countries(@JsonProperty("Country") List<CountryDto> countryDtos){}
    }
}
