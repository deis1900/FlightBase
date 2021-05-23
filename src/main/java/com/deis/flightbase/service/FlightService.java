package com.deis.flightbase.service;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightService {
    Flight findFlightById(Long id);

    Flight create(Flight flight, FlightStatus pending);

    List<Flight> getProblemFlightList(FlightStatus active);

    List<Flight> findLateFlights(FlightStatus completed);

    void update(Flight flight, FlightStatus flightStatus);

    List<Flight> getFlightsByStatus(FlightStatus status);
}

