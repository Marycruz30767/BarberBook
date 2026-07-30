package com.barber.demo.dao;

import com.barber.demo.domain.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {

    List<Empleado> findByActivoTrue();
}
