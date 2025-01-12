package pl.edu.pjatk.luftzug.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.Map;

public record Names(@JsonProperty("Name") JsonNode nameList){}