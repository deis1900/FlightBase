package com.deis.flightbase.model;

import com.deis.flightbase.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight", schema = "flightbase")
public class Flight implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonView(Views.Public.class)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatus flightStatus;

    @JsonView(Views.Public.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "air_company_id", referencedColumnName = "id")
    private AirCompany airCompany;

    @JsonView(Views.Public.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name="airplane_id")
    private Airplane airplane;

    @JsonView(Views.Public.class)
    @Column(name = "departure_country", nullable = false)
    private String departureCountry;

    @JsonView(Views.Public.class)
    @Column(name = "destination_country", nullable = false)
    private String destinationCountry;

    @JsonView(Views.Public.class)
    @Column(name = "distance", nullable = false)
    private Integer distance;

    @JsonView(Views.Public.class)
    @Column(name = "estimated_flight_time", columnDefinition = "TIME", nullable = false)
    private LocalTime estimatedFlightTime;

    @JsonView(Views.Public.class)
    @Column(name = "ended_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime endedDate;

    @JsonView(Views.Public.class)
    @Column(name = "delay_started_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime delayStartedDate;

    @JsonView(Views.Public.class)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && flightStatus == flight.flightStatus && Objects.equals(airCompany, flight.airCompany) && Objects.equals(airplane, flight.airplane) && Objects.equals(departureCountry, flight.departureCountry) && Objects.equals(destinationCountry, flight.destinationCountry) && Objects.equals(distance, flight.distance) && Objects.equals(estimatedFlightTime, flight.estimatedFlightTime) && Objects.equals(endedDate, flight.endedDate) && Objects.equals(delayStartedDate, flight.delayStartedDate) && Objects.equals(createdDate, flight.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightStatus, airCompany, airplane, departureCountry, destinationCountry, distance, estimatedFlightTime, endedDate, delayStartedDate, createdDate);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightStatus=" + flightStatus +
                ", airCompany=" + airCompany +
                ", airplane=" + airplane +
                ", departureCountry='" + departureCountry + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", distance=" + distance +
                ", estimatedFlightTime=" + estimatedFlightTime +
                ", endedDate=" + endedDate +
                ", delayStartedDate=" + delayStartedDate +
                ", createdDate=" + createdDate +
                '}';
    }
}

