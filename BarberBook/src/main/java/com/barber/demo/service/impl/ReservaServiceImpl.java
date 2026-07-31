package com.barber.demo.service.impl;

import com.barber.demo.dao.ReservaDao;
import com.barber.demo.domain.Horario;
import com.barber.demo.domain.Reserva;
import com.barber.demo.service.HorarioService;
import com.barber.demo.service.ReservaService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barber.demo.domain.Usuario;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaDao reservaDao;

    @Autowired
    private HorarioService horarioService;

    @Override
    public List<Reserva> getReservas() {
        return reservaDao.findAll();
    }

    @Override
    public Reserva getReserva(Reserva reserva) {
        return reservaDao.findById(reserva.getIdReserva()).orElse(null);
    }

    @Override
    public void save(Reserva reserva) {
        reservaDao.save(reserva);
    }

    @Override
    public void delete(Reserva reserva) {
        reservaDao.delete(reserva);
    }

    @Override
    public List<Reserva> getReservasPorUsuario(Usuario usuario) {
        return reservaDao.findByUsuarioOrderByFechaDescHoraDesc(usuario);
    }

    @Override
    public void cancelarReserva(Integer idReserva, Usuario usuarioSesion) {
        if (usuarioSesion == null) {
            throw new IllegalStateException("Debe iniciar sesión para cancelar una cita.");
        }

        Reserva reserva = reservaDao.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException("La reserva indicada no existe."));

        if (reserva.getUsuario() == null
                || !reserva.getUsuario().getIdUsuario().equals(usuarioSesion.getIdUsuario())) {
            throw new IllegalStateException("No tiene permiso para cancelar esta reserva.");
        }

        if ("Cancelada".equalsIgnoreCase(reserva.getEstado())) {
            throw new IllegalStateException("Esta cita ya se encuentra cancelada.");
        }

        LocalDateTime fechaHoraReserva = LocalDateTime.of(reserva.getFecha(), reserva.getHora());
        if (fechaHoraReserva.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("No es posible cancelar una cita que ya pasó.");
        }

        reserva.setEstado("Cancelada");
        reservaDao.save(reserva);

        Horario horario = horarioService.buscarPorEmpleadoFechaHora(
                reserva.getEmpleado(), reserva.getFecha(), reserva.getHora());
        if (horario != null) {
            horario.setDisponible(true);
            horarioService.save(horario);
        }
    }
}
