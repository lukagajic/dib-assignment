package com.lukagajic.dibassignment.service;

import com.lukagajic.dibassignment.api.ApiBeer;
import com.lukagajic.dibassignment.api.MashTemp;
import com.lukagajic.dibassignment.entity.Beer;
import com.lukagajic.dibassignment.entity.Temperature;
import com.lukagajic.dibassignment.repository.BeerRepository;
import com.lukagajic.dibassignment.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final TemperatureRepository temperatureRepository;

    @Value("${api.random-route}")
    private String apiRandomRoute;

    public BeerServiceImpl(BeerRepository beerRepository, TemperatureRepository temperatureRepository) {
        this.beerRepository = beerRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public List<Beer> getAll() {
        return beerRepository.findAll();
    }

    @Override
    public Optional<Beer> getById(Integer id) {
        return beerRepository.findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        Optional<Beer> foundBeer = this.getById(id);
        boolean isDeleted = false;

        if (foundBeer.isPresent()) {
            beerRepository.deleteById(id);
            isDeleted = true;
        }

        return isDeleted;
    }

    @Override
    @Transactional
    public void populateDatabase(int numberOfBeers) {
        WebClient.Builder builder = WebClient.builder();

        for (int i = 0; i < numberOfBeers; i++) {
            ApiBeer[] beers = builder.build()
                    .get()
                    .uri(apiRandomRoute)
                    .retrieve()
                    .bodyToMono(ApiBeer[].class)
                    .block();
            try {
                Beer newBeer = new Beer();
                newBeer.setName(beers[0].getName());
                newBeer.setDescription(beers[0].getDescription());

                newBeer.setMashTemp(
                        (Arrays.stream(beers[0].getMethod().getMashTemp())).map(mashTemp -> new Temperature(mashTemp.getTemp().getValue(), mashTemp.getTemp().getUnit())).collect(Collectors.toSet())
                );

                Set<Temperature> temperatures = new HashSet<>();
                
                /*
                    This is necessary because for some reason H2 database doesn't
                    apply CascadeType of PERSIST
                */
                for (MashTemp mt : beers[0].getMethod().getMashTemp()) {
                    Temperature t = new Temperature();

                    t.setUnit(mt.getTemp().getUnit());
                    t.setValue(mt.getTemp().getValue());
                    t.setBeer(newBeer);

                    Temperature saved = temperatureRepository.save(t);
                }
                newBeer.setMashTemp(temperatures);

                Beer saved = beerRepository.saveAndFlush(newBeer);
            } catch (DataIntegrityViolationException e) {
                i--;
            }
        }
    }
}
