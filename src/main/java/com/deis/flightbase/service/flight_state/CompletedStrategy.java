package com.deis.flightbase.service.flight_state;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;

import java.time.LocalDateTime;

public class CompletedStrategy implements FlightStrategy {

    @Override
    public Flight handleFlightStatus(Flight flight) {
            flight.setFlightStatus(FlightStatus.COMPLETED);
            flight.setEndedDate(LocalDateTime.now());
            return flight;
    }
}
