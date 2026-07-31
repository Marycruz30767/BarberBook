package com.barber.demo.dao;

import com.barber.demo.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
}