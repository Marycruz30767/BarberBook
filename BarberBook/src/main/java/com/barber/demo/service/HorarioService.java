package com.barber.demo.service;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Horario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HorarioService {

    List<Horario> getHorarios();

    Horario getHorario(Horario horario);

    void save(Horario horario);

    void delete(Horario horario);

    Horario buscarPorEmpleadoFechaHora(Empleado empleado, LocalDate fecha, LocalTime hora);
}
