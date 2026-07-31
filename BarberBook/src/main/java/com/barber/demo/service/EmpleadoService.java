package com.barber.demo.service;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Usuario;

public interface EmpleadoService {

    Empleado getEmpleadoPorUsuario(Usuario usuario);
}