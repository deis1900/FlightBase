package com.deis.flightbase.service;

import com.deis.flightbase.model.Airplane;
import com.deis.flightbase.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService{

    private final AirplaneRepository airplaneRepository;
    private final AirCompanyService airCompanyService;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyService airCompanyService) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyService = airCompanyService;
    }

    @Override
    public Optional<Airplane> findBySerialNumber(String serialNumber) {
        return airplaneRepository.findByFactorySN(serialNumber);
    }

    @Override
    public Long save(Airplane airplane) {
        Airplane savedPlane = airplaneRepository.save(airplane);

        return savedPlane.getId();
    }

    @Override
    public void update(Airplane airplane, Long id) {
        airplane.setAirCompany(airCompanyService.getById(id));
        airplaneRepository.saveAndFlush(airplane);
    }
}
