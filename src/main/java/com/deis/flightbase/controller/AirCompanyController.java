package com.deis.flightbase.controller;

import com.deis.flightbase.config.Views;
import com.deis.flightbase.model.AirCompany;
import com.deis.flightbase.model.FlightStatus;
import com.deis.flightbase.service.AirCompanyService;
import com.deis.flightbase.util.CustomErrorType;
import com.fasterxml.jackson.annotation.JsonView;
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
@RequestMapping(value = "/air_company")
@Tag(name = "AirCompany Controller", description = "Communicate with air company.")
public class AirCompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirCompanyController.class.getName());

    private final AirCompanyService airCompanyService;

    public AirCompanyController(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
    }

    @JsonView(Views.Internal.class)
    @Operation(
            summary = "get list of air Company",
            description = "1) Simple CRUD operations for Air company entity"
    )
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirCompany>> getAllAirCompany() {
        List<AirCompany> airCompanyList = airCompanyService.findAll();
        return new ResponseEntity<>(airCompanyList, HttpStatus.OK);
    }

    @JsonView(Views.Internal.class)
    @Operation(
            summary = "get air company by name",
            description = "1) Simple CRUD operations for Air company entity"
    )
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirCompany> getAirCompanyByName(@PathVariable("name") String name) {
        LOGGER.info("Fetching air company with name " + name);
        return new ResponseEntity<>(airCompanyService.getAirCompanyByName(name), HttpStatus.OK);
    }

    @Operation(
            summary = "save new air company",
            description = "1) Simple CRUD operations for Air company entity"
    )
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAirCompany(@Valid @RequestBody
                                      @Parameter(description = "Generated air company.") AirCompany airCompany) {
        LOGGER.info("Creating air company " + airCompany.getName());
        if (airCompanyService.isExists(airCompany)) {
            LOGGER.error("Air company already exist " + airCompany.getName());
            return new ResponseEntity<>(
                    new CustomErrorType("Air company with name '"
                            + airCompany.getName() + "' already exist!"),
                    HttpStatus.CONFLICT);
        }
        Long id = airCompanyService.save(airCompany);
        airCompany.setId(id);
        return new ResponseEntity<>(airCompany, HttpStatus.CREATED);
    }

    @Operation(
            summary = "update film",
            description = "1) Simple CRUD operations for Air company entity. Save and flush film to db."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAirCompany(@PathVariable("id") Long id, @Valid @RequestBody AirCompany airCompany) {

        LOGGER.info("Update air company with id " + id);
        AirCompany airCompanyDB = airCompanyService.getById(id);

        if (airCompanyDB == null) {
            LOGGER.error("Unable to update. AirCompany with id '" + id + "' not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (airCompany.equals(airCompanyDB)) {
            String message = "no changes have been made in the company with name " + airCompany.getName() + ".";
            LOGGER.error(message);
            return new ResponseEntity<>( new CustomErrorType(message),
                    HttpStatus.CONFLICT);
        }
        if (!airCompany.getFoundedDate().equals(airCompanyDB.getFoundedDate())) {
            String message = "It is inadmissible to change the date of foundation of the company.";
            LOGGER.error(message);
            return new ResponseEntity<>( new CustomErrorType(message),
                    HttpStatus.CONFLICT);
        }
        airCompany.setId(id);
        airCompanyService.update(airCompany);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "delete air company",
            description = "1) Simple CRUD operations for Air company entity. Delete air company from database by id."
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAirCompany(@PathVariable("id") Long id) {
        LOGGER.info("Fetching & Deleting air company with id " + id);
        AirCompany airCompany = airCompanyService.getById(id);
        if (airCompany == null) {
            LOGGER.error("Unable to delete. Air company with id '" + id + "' not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        airCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "3) Endpoint to find all Air Company Flights by status (use company name for identification " +
                    "of Air Company)"
    )
    @GetMapping(value = "/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirCompany>> getAirCompanyByStatus(@PathVariable("status")FlightStatus status){
        List<AirCompany> flightList = airCompanyService.getListAirCompany(status);
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }
}
