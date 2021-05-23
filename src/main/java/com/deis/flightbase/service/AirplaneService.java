package com.deis.flightbase.service;

import com.deis.flightbase.model.Airplane;

import java.util.Optional;

public interface AirplaneService {

    Optional<Airplane> findBySerialNumber(String serialNumber);

    Long save(Airplane airplane);

    void update(Airplane airplane, Long id);
}
