package com.barber.demo.dao;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {

    Empleado findByUsuario(Usuario usuario);

    List<Empleado> findByActivoTrue();
}
