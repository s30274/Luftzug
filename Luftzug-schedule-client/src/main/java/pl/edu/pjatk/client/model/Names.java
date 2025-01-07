package pl.edu.pjatk.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Names(@JsonProperty("Name") Object name){}