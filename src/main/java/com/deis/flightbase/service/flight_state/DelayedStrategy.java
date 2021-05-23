package com.deis.flightbase.service.flight_state;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;

import java.time.LocalDateTime;

public class DelayedStrategy implements FlightStrategy {

    @Override
    public Flight handleFlightStatus(Flight flight) {
        flight.setFlightStatus(FlightStatus.DELAYED);
        flight.setDelayStartedDate(LocalDateTime.now());
        return flight;
    }
}
