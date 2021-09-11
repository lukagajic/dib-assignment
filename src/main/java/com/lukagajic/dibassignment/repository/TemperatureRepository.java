package com.lukagajic.dibassignment.repository;

import com.lukagajic.dibassignment.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {
}
