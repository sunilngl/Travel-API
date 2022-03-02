package com.travel.api.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@JsonInclude(NON_NULL)
@Value
@Data
public class LocationsDTO implements Serializable {

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("coordinates")
	private Coordinates coordinates;

	@JsonProperty("parent")
	private LocationsDTO parent;

}
