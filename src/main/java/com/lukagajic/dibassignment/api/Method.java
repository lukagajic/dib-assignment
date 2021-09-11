package com.lukagajic.dibassignment.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Method {
    private MashTemp[] mashTemp;

    public MashTemp[] getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(MashTemp[] mashTemp) {
        this.mashTemp = mashTemp;
    }
}
