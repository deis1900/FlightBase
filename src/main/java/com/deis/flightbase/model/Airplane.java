package com.deis.flightbase.model;

import com.deis.flightbase.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "airplane", schema = "flightbase")
public class Airplane implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonView(Views.Public.class)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonView(Views.Public.class)
    @Column(name = "factory_serial_number", unique = true, nullable = false)
    private String factorySN;

    @JsonView(Views.Internal.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;

    @JsonView(Views.Internal.class)
    @OneToMany(mappedBy = "airplane", cascade = CascadeType.REMOVE)
    private Set<Flight> flights;

    @JsonView(Views.Public.class)
    @Column(name = "number_of_flights", unique = true, nullable = false)
    private String numberOfFlight;

    @JsonView(Views.Public.class)
    @Column(name = "flight_distance")
    private Integer flightDistance;

    @JsonView(Views.Public.class)
    @Column(name = "fuel_capacity", nullable = false)
    private Integer fuelCapacity;

    @JsonView(Views.Public.class)
    @Column(name = "type", nullable = false)
    private String type;

    @JsonView(Views.Public.class)
    @Column(name = "created_at", columnDefinition = "DATE", nullable = false)
    private LocalDate createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(id, airplane.id) && Objects.equals(name, airplane.name) && Objects.equals(factorySN, airplane.factorySN) && Objects.equals(airCompany, airplane.airCompany) && Objects.equals(numberOfFlight, airplane.numberOfFlight) && Objects.equals(flightDistance, airplane.flightDistance) && Objects.equals(fuelCapacity, airplane.fuelCapacity) && Objects.equals(type, airplane.type) && Objects.equals(createdDate, airplane.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, factorySN, airCompany, numberOfFlight, flightDistance, fuelCapacity, type, createdDate);
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", factorySN='" + factorySN + '\'' +
                ", airCompany=" + airCompany +
                ", numberOfFlight='" + numberOfFlight + '\'' +
                ", flightDistance=" + flightDistance +
                ", fuelCapacity=" + fuelCapacity +
                ", type='" + type + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
