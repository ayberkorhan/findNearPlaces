package com.example.demo.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Models.Location;
import com.example.demo.Repository.LocationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class LogicServices {
	
	
    private final  RestTemplate restTemplate;
    private final LocationRepository locationRepository;

    public LogicServices(RestTemplate restTemplate, LocationRepository locationRepository){
        this.restTemplate= restTemplate;
        this.locationRepository=locationRepository;
    }

    @Value( "${google.url}" )
    private String url;


    public  JsonNode  locationLogic(String latitude,String longitude,String radius) throws JsonMappingException, JsonProcessingException{
        
        ObjectMapper objectMapper = new ObjectMapper();
        Location location = new Location();

        String position = latitude+","+longitude;



       location = locationRepository.findLocationByRequest(latitude, longitude, radius);

       if(location == null){
        Location locationF = new Location();

        String response = restTemplate.getForObject(url, String.class, position,radius);

        locationF.setLatitude(latitude);
        locationF.setLongitude(longitude);
        locationF.setRadius(radius);
        locationF.setLocationOfPlaces(response);

        locationRepository.save(locationF);

        return objectMapper.readTree(locationF.getLocationOfPlaces());

       }

       return objectMapper.readTree(location.getLocationOfPlaces());

    }
    
}
