package com.deis.flightbase.model;

import com.deis.flightbase.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "air_company", schema = "flightbase")
public class AirCompany implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @JsonView(Views.Public.class)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "airCompany", cascade = CascadeType.ALL)
    private List<Airplane> airplanes;

    @JsonView(Views.Public.class)
    @Column(name = "company_type")
    private String companyType;

    @JsonView(Views.Public.class)
    @Column(name = "founded_at", columnDefinition = "DATE", nullable = false)
    private LocalDate foundedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirCompany that = (AirCompany) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(companyType, that.companyType) && Objects.equals(foundedDate, that.foundedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, companyType, foundedDate);
    }

    @Override
    public String toString() {
        return "AirCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyType='" + companyType + '\'' +
                ", foundedDate=" + foundedDate +
                '}';
    }
}
