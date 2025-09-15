package com.hotel.reservas.repository;

import com.hotel.reservas.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    Optional<Direccion> findByCalleAndNumPuertaAndCiudadAndDepartamentoAndPais(
        String calle, String numPuerta, String ciudad, String departamento, String pais);
}