package com.example.demo.Controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Models.Location;
import com.example.demo.Services.LogicServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("api/v1/location")
@PreAuthorize("hasRole('admin')")
public class LocationController {

    private final LogicServices logicServices;

    public LocationController(RestTemplate restTemplate, LogicServices logicServices) {

        this.logicServices = logicServices;
    }

    @GetMapping()
    public ResponseEntity<JsonNode> getLocation(@RequestParam String lat, @RequestParam String lng,
            @RequestParam String radius) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {

        return ResponseEntity.ok(logicServices.locationLogic(lat,lng,radius));

    }

}
