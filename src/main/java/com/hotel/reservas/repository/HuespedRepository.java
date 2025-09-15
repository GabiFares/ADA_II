package com.hotel.reservas.repository;
import java.util.Optional;

import com.hotel.reservas.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {
    Optional<Huesped> findByCi(String ci);
}


