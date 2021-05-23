package com.deis.flightbase.controller;

import com.deis.flightbase.model.Airplane;
import com.deis.flightbase.service.AirplaneService;
import com.deis.flightbase.util.CustomErrorType;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/airplane")
@Tag(name = "Airplane Controller", description = "Communicate with airplane")
public class AirplaneController {

    private final Logger LOGGER = LoggerFactory.getLogger(AirplaneController.class.getName());

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @Operation(summary = "add new airplane",
            description = " 5) Endpoint to add new Airplane " +
                    "(Could be assign to a company immediately or moved later). !!!Serial number should be other!!!")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAirplane(@Valid @RequestBody @Parameter(description = "generated airplane")
                                                 Airplane airplane) {

        Optional<Airplane> airplaneDB = airplaneService.findBySerialNumber(airplane.getFactorySN());
        if (airplaneDB.isEmpty()) {
            LOGGER.info("Save new airplane: " + airplane.toString());
            Long newId = airplaneService.save(airplane);
            return new ResponseEntity<>(newId, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(airplane, HttpStatus.CONFLICT);
    }

    @Operation(summary = "2) Endpoint to move airplanes between companies (simple endpoint to reassign airplane to " +
            "another company).")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAirCompany(@PathVariable("id") Long id, @Valid @RequestBody
    @Parameter(description = "entity for update") Airplane airplane) {

        Optional<Airplane> data = airplaneService.findBySerialNumber(airplane.getFactorySN());
        if (data.isPresent()) {
            if (!data.get().getAirCompany().getName().equals(airplane.getAirCompany().getName())) {
                airplaneService.update(airplane, id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new CustomErrorType("Have no change"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new CustomErrorType("Airplane with factory serial number "
                + airplane.getFactorySN() + " isn't exist."), HttpStatus.NOT_FOUND);
    }
}
