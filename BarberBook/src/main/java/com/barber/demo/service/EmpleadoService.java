package com.barber.demo.service;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Usuario;
import java.util.List;

public interface EmpleadoService {

    List<Empleado> getEmpleados();

    Empleado getEmpleado(Empleado empleado);

    void save(Empleado empleado);

    void delete(Empleado empleado);

    void registrarEmpleado(Usuario datosUsuario, String especialidad);
}
