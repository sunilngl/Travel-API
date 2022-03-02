package com.travel.api.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.travel.api.model.AirportDetailsDTO;
import com.travel.api.model.LocationsDTO;

public interface IAirportService {

	public List<LocationsDTO> getAirportDetails();

	public LocationsDTO getAirportDetailsByCode(String code);
}
