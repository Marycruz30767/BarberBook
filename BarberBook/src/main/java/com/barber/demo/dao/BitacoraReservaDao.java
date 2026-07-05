package com.barber.demo.dao;

import com.barber.demo.domain.BitacoraReserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitacoraReservaDao extends JpaRepository<BitacoraReserva, Integer> {
}