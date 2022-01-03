package shen.ars.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import shen.ars.model.Airline;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class App1ControllerTest {
    @Autowired private App1Controller app1Controller;

    @BeforeEach
    public void setup() {
        app1Controller.save(new Airline(1, "Alaska", "AS"));
        app1Controller.save(new Airline(2, "Delta", "DL"));
        app1Controller.save(new Airline(3, "American", "AA"));
    }

    @Test
    public void testGetAirlines() {
        List<Airline> airlines = app1Controller.getAirlines();
        assertTrue(airlines != null);
        assertEquals(3, airlines.size());
    }

    @Test
    public void testGetAirlineById() {
        Airline airline = app1Controller.getAirlineById(1L);
        assertTrue(airline != null);
        assertEquals(1, airline.getId());
        assertEquals("Alaska", airline.getName());
        assertEquals("AS", airline.getShortName());
    }

    @Test
    public void testGetAirlineByIdWithInvalidId() {
        ResponseStatusException thrown = assertThrows(ResponseStatusException.class,
                ()-> app1Controller.getAirlineById(9L));
        assertTrue(thrown.getMessage().contains("not found"));
    }
}
