package com.lukagajic.dibassignment.controller;

import com.lukagajic.dibassignment.common.ApiResponse;
import com.lukagajic.dibassignment.entity.Beer;
import com.lukagajic.dibassignment.service.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public List<Beer> getAll() {
        return beerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beer> getById(@PathVariable Integer id) {
        Optional<Beer> foundBeer = beerService.getById(id);

        if (foundBeer.isPresent()) {
            return new ResponseEntity<>(foundBeer.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ApiResponse populateDatabase() {
        int beerCount = this.beerService.getAll().size();

        if (beerCount == 0) {
            return new ApiResponse(false, "There are no beers in database!");
        }

        if (beerCount == 10) {
            return new ApiResponse(false, "The database is full");
        }

        this.beerService.populateDatabase(10 - beerCount);

        return new ApiResponse(true, "Succesfully added " + (10 - beerCount) + " beers to the database!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer id) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;

        ApiResponse response = new ApiResponse(false, "Beer with an id of " + id + " could not be found!");
        boolean isDeleted = beerService.deleteById(id);

        if (isDeleted) {
            response.setSuccessful(true);
            response.setMessage("Beer with an id of " + id + " successfully deleted!");
            statusCode = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, statusCode);
    }
}
