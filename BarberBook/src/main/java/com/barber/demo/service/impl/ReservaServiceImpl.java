package com.barber.demo.service.impl;

import com.barber.demo.dao.ReservaDao;
import com.barber.demo.domain.Reserva;
import com.barber.demo.service.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barber.demo.domain.Usuario;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaDao reservaDao;

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
    return reservaDao.findByUsuario(usuario);
}
}