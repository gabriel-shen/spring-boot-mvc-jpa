package shen.ars.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import shen.ars.model.Airline;
import shen.ars.repository.AirlineRepository;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class App1Controller {
    private final AirlineRepository airlineRepository;

    @Autowired
    App1Controller(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @GetMapping("airlines")
    @Operation(summary = "Get a list of all airlines")
    public List<Airline> getAirlines() {
        log.trace("getAirlines()");
        return airlineRepository.findAll();
    }

    @GetMapping("airlines/{id}")
    @Operation(summary = "Get an airline")
    public Airline getAirlineById(@PathVariable("id") Long id) {
        log.trace("getAirlineById({})", id);
        Optional<Airline> airlineOptional = airlineRepository.findById(id);
        if (!airlineOptional.isPresent()) {
            String msg = "airline of ID " + id.toString() + " not found";
            log.warn(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        return airlineOptional.get();
    }

    @PostMapping("airlines")
    @Operation(summary = "Add an airline")
    public void save(@RequestBody Airline airline) {
        log.trace("save({})", airline.toString());
        airlineRepository.save(airline);
    }

    @PutMapping("airlines/{id}")
    @Operation(summary = "Update an airline")
    public void update(@PathVariable Long id, @RequestBody Airline airline) {
        log.trace("update({}, {})", id, airline.toString());
        Optional<Airline> airlineOptional = airlineRepository.findById(id);
        if (!airlineOptional.isPresent()) {
            String msg = "airline of ID " + id.toString() + " not found";
            log.warn(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }

        airline.setId(id);
        airlineRepository.save(airline);
    }

    @DeleteMapping("airlines/{id}")
    @Operation(summary = "Delete an airline")
    public void delete(@PathVariable("id") Long id) {
        log.trace("delete({})", id);
        airlineRepository.deleteById(id);
    }
}

