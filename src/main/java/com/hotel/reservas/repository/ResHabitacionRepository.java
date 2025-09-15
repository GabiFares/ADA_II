package com.hotel.reservas.repository;

import com.hotel.reservas.model.ResHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResHabitacionRepository extends JpaRepository<ResHabitacion, Long> {
}