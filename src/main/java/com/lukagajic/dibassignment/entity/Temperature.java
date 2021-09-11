package com.lukagajic.dibassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "temperature")
public class Temperature {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temperature_id")
    private Integer temperatureId;

    @Column
    private int value;

    @Column
    private String unit;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    public Temperature() {
    }

    public Temperature(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Integer getTemperatureId() {
        return temperatureId;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void setTemperatureId(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
