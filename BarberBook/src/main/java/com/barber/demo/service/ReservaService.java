package com.barber.demo.service;

import com.barber.demo.domain.Reserva;
import com.barber.demo.domain.Usuario;
import java.util.List;
import com.barber.demo.domain.Empleado;
public interface ReservaService {

    List<Reserva> getReservas();

    Reserva getReserva(Reserva reserva);

    void save(Reserva reserva);

    void delete(Reserva reserva);

    List<Reserva> getReservasPorUsuario(Usuario usuario);
List<Reserva> getReservasPorEmpleado(Empleado empleado);
    void cancelarReserva(Integer idReserva, Usuario usuarioSesion);
    
    long getTotalReservas();

long getCantidadPorEstado(String estado);
List<Reserva> getTodasLasReservas();

List<Reserva> getReservasPorEstado(String estado);
}
