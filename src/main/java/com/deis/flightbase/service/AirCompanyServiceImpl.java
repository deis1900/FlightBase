package com.deis.flightbase.service;

import com.deis.flightbase.model.AirCompany;
import com.deis.flightbase.model.Flight;
import com.deis.flightbase.model.FlightStatus;
import com.deis.flightbase.repository.AirCompanyRepository;
import com.deis.flightbase.util.exception_handler.NoSuchElementFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepository repository;
    private final FlightService flightService;

    public AirCompanyServiceImpl(AirCompanyRepository airCompanyRepository, FlightService flightService) {
        this.repository = airCompanyRepository;
        this.flightService = flightService;
    }

    @Override
    @Transactional
    public Long save(AirCompany airCompany) {
        airCompany.setFoundedDate(LocalDate.now());
        AirCompany savedAirCompany = repository.save(airCompany);
        return savedAirCompany.getId();
    }

    @Override
    @Transactional
    public void update(AirCompany airCompany) {
        repository.saveAndFlush(airCompany);
    }

    @Override
    public boolean isExists(AirCompany airCompany) {
        return repository.existsAirCompanyByName(airCompany.getName());
    }

    @Override
    public AirCompany getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException("Air company with id " + id + " not found", new Throwable()));
    }

    @Override
    public AirCompany getAirCompanyByName(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new NoSuchElementFoundException("Air company with name " + name + " not found", new Throwable()));
    }

    @Override
    public List<AirCompany> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AirCompany> getListAirCompany(FlightStatus status) {
        return flightService.getFlightsByStatus(status)
                .stream()
                .map(Flight::getAirCompany)
                .collect(Collectors.toList());
    }
}
