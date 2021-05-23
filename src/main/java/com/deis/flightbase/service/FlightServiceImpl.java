package com.deis.flightbase.service;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;
import com.deis.flightbase.repository.FlightRepository;
import com.deis.flightbase.service.flight_state.*;
import com.deis.flightbase.util.exception_handler.IllegalFlightStatusException;
import com.deis.flightbase.util.exception_handler.NoSuchElementFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class.getName());

    private final FlightRepository repository;

    public FlightServiceImpl(FlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flight findFlightById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException("Flight with id " + id + " not found", new Throwable()));
    }

    @Override
    public List<Flight> getProblemFlightList(FlightStatus active) {
        List<Flight> flightList = repository.findAllByFlightStatus(active);

        return flightList.stream()
                .filter(flight -> flight.getCreatedDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> findLateFlights(FlightStatus completed) {
        List<Flight> flightList = repository.findAllByFlightStatus(FlightStatus.COMPLETED);

        return flightList.stream()
                .filter(flight -> countTime(
                        flight.getCreatedDate(),
                        flight.getEndedDate(),
                        flight.getEstimatedFlightTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsByStatus(FlightStatus status) {
        return repository.findAllByFlightStatus(status);
    }

    @Override
    @Transactional
    public Flight create(Flight flight, FlightStatus pending) {
        updateFlightStatus(flight, pending);
        LOGGER.info("Save flight " + flight.toString());
        return repository.save(flight);
    }

    @Override
    @Transactional
    public void update(Flight flight, FlightStatus flightStatus) {
        Flight f = updateFlightStatus(flight, flightStatus);
        LOGGER.info("Update flight " + flight.toString());
        repository.saveAndFlush(f);
    }

    Flight updateFlightStatus(Flight flight, FlightStatus flightStatus) {
        FlightStrategy strategy = null;
        switch (flightStatus) {

            case PENDING:
                strategy = new PendingStrategy();
                break;

            case ACTIVE:
                if (flight.getFlightStatus() == FlightStatus.PENDING) {
                    strategy = new ActiveStrategy();
                } else throw new IllegalFlightStatusException("Cannot change the status " + flight.getFlightStatus() +
                        " to status ACTIVE.", new Throwable());
                break;

            case DELAYED:
                if (flight.getFlightStatus() == FlightStatus.ACTIVE) {
                    strategy = new DelayedStrategy();
                } else throw new IllegalFlightStatusException("Cannot change the status " + flight.getFlightStatus() +
                        " to status DELAYED.", new Throwable());
                break;

            case COMPLETED:
                if (flight.getFlightStatus() == FlightStatus.ACTIVE ||
                        flight.getFlightStatus() == FlightStatus.DELAYED) {
                    strategy = new CompletedStrategy();
                } else throw new IllegalFlightStatusException("Cannot change the status " + flight.getFlightStatus() +
                        " to status COMPLETED.", new Throwable());
                break;
        }
        if (strategy != null) {
            return strategy.handleFlightStatus(flight);
        } else throw new IllegalFlightStatusException("Flight not have status", new Throwable());
    }

//    TODO make
    boolean countTime(LocalDateTime start, LocalDateTime end, LocalTime estimatedFlightTime){
        return true;
    }
}
