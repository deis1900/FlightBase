package com.deis.flightbase.controller;

import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;
import com.deis.flightbase.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/flight")
@Tag(name = "Flight Controller", description = "Communicate with flight")
public class FlightController {

    private final Logger LOGGER = LoggerFactory.getLogger(FlightController.class.getName());

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }



    @Operation(
            summary = "4) Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago."
    )
    @GetMapping(value = "/more24", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProblemFlights(){
        List<Flight> flightList = flightService.getProblemFlightList(FlightStatus.ACTIVE);
        return new ResponseEntity<>(flightList, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Endpoint to find all Flights in COMPLETED status and difference between\n" +
                    "started and ended time is bigger than estimated flight time."
    )
    @GetMapping(value = "/late", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findLateFlights(){
        List<Flight> flightList = flightService.findLateFlights(FlightStatus.COMPLETED);
        return new ResponseEntity<>(flightList, HttpStatus.CREATED);
    }

    @Operation(
            summary = "6)",
            description = "Endpoint to add new Flight (set status to PENDING)"
    )
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFlight(@Valid @RequestBody
                                                  @Parameter(description = "Generated flight") Flight flight){
        Flight flt = flightService.create(flight, FlightStatus.PENDING);
        LOGGER.info("Save new flight" + flt.toString());
        return new ResponseEntity<>(flt.getId(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "7)",
            description = "Endpoint to change Flight status: " +
                    "if status to change is DELAYED – set delay started at " +
                    "if status to change is ACTIVE – set started at " +
                    "if status to change is COMPLETED set ended at "
    )
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeFlightStatus(@PathVariable("id") Long id, @RequestBody
            @Parameter(description = "update status for flight") FlightStatus flightStatus){
        Flight flight = flightService.findFlightById(id);
        flightService.update(flight, flightStatus);
        LOGGER.info("Flight late. Starting the delayed flight date.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
