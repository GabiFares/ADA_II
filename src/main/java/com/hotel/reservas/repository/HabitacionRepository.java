package com.hotel.reservas.repository;

import com.hotel.reservas.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
