package com.hotel.reservas.repository;

import com.hotel.reservas.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}