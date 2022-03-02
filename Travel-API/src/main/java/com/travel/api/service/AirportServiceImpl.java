package com.travel.api.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.api.controller.TravelAPIController;
import com.travel.api.model.AirportDetailsDTO;
import com.travel.api.model.EmbeddedDTO;
import com.travel.api.model.LocationsDTO;

@Service
public class AirportServiceImpl implements IAirportService {

	Logger logger = LogManager.getLogger(TravelAPIController.class);

	private final static String AIRPORT_DETAILS = "AIRPORT DETAILS";
	private final static String GET_AIRPORT_DETAILS_BY_CODE = "AIRPORT DETAILS BY CODE";

	@Value("${rest_API_url}")
	private String rest_API_url;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;

	@Override
	public List<LocationsDTO> getAirportDetails() {

		RestTemplate rest = new RestTemplate();
		List<LocationsDTO> AirportLoc = null;

		logger.info(AIRPORT_DETAILS, " API Received");

		String credentials = username + ":" + password;
		byte[] plainCredsBytes = credentials.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		try {
			HttpEntity<String> request = new HttpEntity<String>(headers);
			ResponseEntity<AirportDetailsDTO> res = rest.exchange(rest_API_url, HttpMethod.GET, request,
					AirportDetailsDTO.class);
			AirportDetailsDTO airportDetails = res.getBody();
			AirportLoc = airportDetails.get_embedded().getLocations();

		} catch (Exception e) {
			logger.error(AIRPORT_DETAILS, " Rest server throwing exception is : ", e);
		}

		return AirportLoc;
	}

	@Override
	public LocationsDTO getAirportDetailsByCode(String code) {
		RestTemplate rest = new RestTemplate();
		LocationsDTO locations = null;

		logger.info(GET_AIRPORT_DETAILS_BY_CODE, " API Received");
		
		String credentials = username + ":" + password;
		byte[] plainCredsBytes = credentials.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		try {
			HttpEntity<String> request = new HttpEntity<String>(headers);
			ResponseEntity<LocationsDTO> res = rest.exchange(rest_API_url + code, HttpMethod.GET, request,
					LocationsDTO.class);
			locations = res.getBody();

		} catch (Exception e) {
			logger.error(GET_AIRPORT_DETAILS_BY_CODE, " Rest server throwing exception is : ", e);
		}

		return locations;
	}

}
