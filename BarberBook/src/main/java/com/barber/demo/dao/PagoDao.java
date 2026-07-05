package com.barber.demo.dao;

import com.barber.demo.domain.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoDao extends JpaRepository<Pago, Integer> {
}