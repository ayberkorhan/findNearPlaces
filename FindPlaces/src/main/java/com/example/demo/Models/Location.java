package com.example.demo.Models;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;



@Entity
@Table(name = "location")
public class Location {


    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    @JsonIgnore
    private UUID uuid = UUID.randomUUID();


    @Column(name = "longitude")
    @JsonIgnore
	private String longitude;

	@Column(name = "latitude")
    @JsonIgnore
	private String latitude;

    @Column(name = "radius")
    @JsonIgnore
	private String radius;

	@Column(name = "locationOfPlaces")
    @Type(type = "text")
	private String locationOfPlaces;










    //GETTER AND SETTER

    @Override
	public String toString() {
		return "Location [uuid=" + uuid + ", longitude=" + longitude + ", latitude=" + latitude + ", radius=" + radius
				+ ", locationOfPlaces=" + locationOfPlaces + "]";
	}

	public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
  
    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLocationOfPlaces() {
        return locationOfPlaces;
    }

    public void setLocationOfPlaces(String locationOfPlaces) {
        this.locationOfPlaces = locationOfPlaces;
    }

  public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }



    
}
