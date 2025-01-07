package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(@JsonProperty("TotalCount") int count){}