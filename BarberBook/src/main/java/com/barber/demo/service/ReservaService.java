package com.barber.demo.service;

import com.barber.demo.domain.Reserva;
import com.barber.demo.domain.Usuario;
import java.util.List;

public interface ReservaService {

    List<Reserva> getReservas();

    Reserva getReserva(Reserva reserva);

    void save(Reserva reserva);

    void delete(Reserva reserva);

    List<Reserva> getReservasPorUsuario(Usuario usuario);

    void cancelarReserva(Integer idReserva, Usuario usuarioSesion);
}
