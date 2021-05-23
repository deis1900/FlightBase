package com.deis.flightbase.service;

import com.deis.flightbase.model.AirCompany;
import com.deis.flightbase.model.FlightStatus;

import java.util.List;

public interface AirCompanyService {

    boolean isExists(AirCompany airCompany);

    AirCompany getById(Long id);

    AirCompany getAirCompanyByName(String name);

    List<AirCompany> findAll();

    Long save(AirCompany airCompany);

    void update(AirCompany airCompany);

    void delete(Long id);

    List<AirCompany> getListAirCompany(FlightStatus status);
}
