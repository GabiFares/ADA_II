package com.hotel.reservas.repository;

import com.hotel.reservas.model.Ocupacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcupacionRepository extends JpaRepository<Ocupacion, Long> {
}