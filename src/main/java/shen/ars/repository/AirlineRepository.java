package shen.ars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shen.ars.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {}
