package com.deis.flightbase.service.flight_state;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;

import java.time.LocalDateTime;

public class ActiveStrategy implements FlightStrategy {

    @Override
    public Flight handleFlightStatus(Flight flight) {
        flight.setFlightStatus(FlightStatus.ACTIVE);
        flight.setCreatedDate(LocalDateTime.now());
        return flight;
    }
}
