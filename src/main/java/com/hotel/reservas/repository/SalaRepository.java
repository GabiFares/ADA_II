package com.hotel.reservas.repository;

import com.hotel.reservas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}