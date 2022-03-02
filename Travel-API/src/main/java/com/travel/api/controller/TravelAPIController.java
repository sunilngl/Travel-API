package com.travel.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.travel.api.model.LocationsDTO;
import com.travel.api.service.IAirportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class TravelAPIController {

	Logger logger = LogManager.getLogger(TravelAPIController.class);

	@Autowired
	IAirportService AirportServiceInterface;

	@GetMapping
	public List<LocationsDTO> getAirportDetails()  {
		logger.info(" New API Request to Fetch all location Details ");
		List<LocationsDTO> results = AirportServiceInterface.getAirportDetails();
		return results;
	}

	@GetMapping("/{code}")
	public LocationsDTO getAirportDetailsByCode(@PathVariable("code") final String code) {

		logger.info(" New API Request to Fetch Code based location Details ");

		LocationsDTO airportDto = AirportServiceInterface.getAirportDetailsByCode(code);
		return airportDto;

	}

}
