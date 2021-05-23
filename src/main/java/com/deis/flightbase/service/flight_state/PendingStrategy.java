package com.deis.flightbase.service.flight_state;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;

public class PendingStrategy implements FlightStrategy {

    @Override
    public Flight handleFlightStatus(Flight flight) {
        flight.setFlightStatus(FlightStatus.PENDING);
        flight.setEndedDate(null);
        flight.setCreatedDate(null);
        flight.setDelayStartedDate(null);
    return flight;
    }
}
