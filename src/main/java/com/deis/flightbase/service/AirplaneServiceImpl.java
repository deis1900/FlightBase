package com.deis.flightbase.service;

import com.deis.flightbase.model.AirCompany;
import com.deis.flightbase.model.Airplane;
import com.deis.flightbase.repository.AirCompanyRepository;
import com.deis.flightbase.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService{

    private final AirplaneRepository airplaneRepository;
    private final AirCompanyRepository airCompanyRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyRepository airCompanyRepository) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyRepository = airCompanyRepository;
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
        AirCompany airCompany = airCompanyRepository.getOne(id);
        airplane.setAirCompany(airCompany);
        airplaneRepository.saveAndFlush(airplane);
    }
}
