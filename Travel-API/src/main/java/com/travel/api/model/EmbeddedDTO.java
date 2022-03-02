package com.travel.api.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;

@JsonInclude(NON_NULL)
@Value
public class EmbeddedDTO implements Serializable {

	private List<LocationsDTO> locations;

	public EmbeddedDTO() {
		super();
		this.locations = null;

	}

}
