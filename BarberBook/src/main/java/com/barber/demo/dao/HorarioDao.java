package com.barber.demo.dao;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Horario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioDao extends JpaRepository<Horario, Integer> {

    List<Horario> findByDisponibleTrue();

    Horario findByEmpleadoAndFechaAndHora(Empleado empleado, LocalDate fecha, LocalTime hora);
}
