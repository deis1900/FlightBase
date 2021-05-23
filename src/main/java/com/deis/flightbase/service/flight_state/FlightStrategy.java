package com.deis.flightbase.service.flight_state;

import com.deis.flightbase.model.Flight;

public interface FlightStrategy {

    Flight handleFlightStatus(Flight flight);
}
