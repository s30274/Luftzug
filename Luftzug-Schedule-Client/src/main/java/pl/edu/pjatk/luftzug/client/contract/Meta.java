package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(@JsonProperty("TotalCount") int count){}