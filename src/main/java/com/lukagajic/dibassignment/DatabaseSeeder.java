package com.lukagajic.dibassignment;

import com.lukagajic.dibassignment.service.BeerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private BeerService beerService;

    public DatabaseSeeder(BeerService beerService) {
        this.beerService = beerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.beerService.populateDatabase(10);
    }
}
