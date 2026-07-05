package com.barber.demo.dao;

import com.barber.demo.domain.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioDao extends JpaRepository<Servicio, Integer> {

    List<Servicio> findByActivoTrue();
}