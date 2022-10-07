package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query(value ="SELECT * FROM location u WHERE u.latitude = ?1 and u.longitude = ?2 and u.radius = ?3", nativeQuery = true)
    Location findLocationByRequest(String latitude, String longitude, String radius);




}