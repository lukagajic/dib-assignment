package com.lukagajic.dibassignment.service;

import com.lukagajic.dibassignment.entity.Beer;

import java.util.List;
import java.util.Optional;

public interface BeerService {
    public List<Beer> getAll();
    public Optional<Beer> getById(Integer id);
    public boolean deleteById(Integer id);
    public void populateDatabase(int numberOfBeers);
}
