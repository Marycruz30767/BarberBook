package com.barber.demo.dao;

import com.barber.demo.domain.Reserva;
import com.barber.demo.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaDao extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuario(Usuario usuario);

    List<Reserva> findByUsuarioOrderByFechaDescHoraDesc(Usuario usuario);
}
