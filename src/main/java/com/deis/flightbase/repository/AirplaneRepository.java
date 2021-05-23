package com.deis.flightbase.repository;

import com.deis.flightbase.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByFactorySN(String serialNumber);

}
