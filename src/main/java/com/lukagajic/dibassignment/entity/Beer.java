package com.lukagajic.dibassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private Integer beerId;

    @Column(unique = true)
    private String name;

    @Column(length = 2500)
    private String description;

    @Transient
    private double meanTemperature;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "beer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Temperature> mashTemp = new HashSet<>();

    public Beer() {
    }

    public Beer(Integer beerId, String name, String description, double meanTemperature, Set<Temperature> mashTemp) {
        this.beerId = beerId;
        this.name = name;
        this.description = description;
        this.meanTemperature = meanTemperature;
        this.mashTemp = mashTemp;
    }

    @PostLoad
    private void defineMeanTemperature() {
        this.meanTemperature = this.mashTemp.stream().mapToDouble(temperature -> temperature.getValue()).average().getAsDouble();
    }

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMeanTemperature() {
        return meanTemperature;
    }

    public Set<Temperature> getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(Set<Temperature> mashTemp) {
        this.mashTemp = mashTemp;
    }
}
