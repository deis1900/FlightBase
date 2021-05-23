package com.deis.flightbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan("com.deis.flight.model")
public class FlightbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightbaseApplication.class, args);
	}

}
