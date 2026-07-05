package com.barber.demo.dao;

import com.barber.demo.domain.Horario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioDao extends JpaRepository<Horario, Integer> {

    List<Horario> findByDisponibleTrue();
}