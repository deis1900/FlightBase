package com.deis.flightbase.repository;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByFlightStatusAndAirCompany_Name(FlightStatus flightStatus, String name);

    List<Flight> findAllByFlightStatus(FlightStatus flightStatus);



}
