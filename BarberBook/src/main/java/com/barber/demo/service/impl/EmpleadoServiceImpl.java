package com.barber.demo.service.impl;

import com.barber.demo.dao.EmpleadoDao;
import com.barber.demo.dao.RolDao;
import com.barber.demo.dao.UsuarioDao;
import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Rol;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.EmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Override
    public Empleado getEmpleadoPorUsuario(Usuario usuario) {
        return empleadoDao.findByUsuario(usuario);
    }

    @Override
    public List<Empleado> getEmpleados() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado getEmpleado(Empleado empleado) {
        return empleadoDao.findById(empleado.getIdEmpleado()).orElse(null);
    }

    @Override
    public void save(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
        empleadoDao.delete(empleado);
    }

    @Override
    @Transactional
    public void registrarEmpleado(Usuario datosUsuario, String especialidad) {

        if (esVacio(datosUsuario.getNombre()) || esVacio(datosUsuario.getApellido())
                || esVacio(datosUsuario.getCorreo()) || esVacio(datosUsuario.getContrasena())) {
            throw new IllegalArgumentException("Nombre, apellido, correo y contraseña son obligatorios.");
        }

        if (usuarioDao.findByCorreo(datosUsuario.getCorreo()) != null) {
            throw new IllegalStateException("Ya existe un usuario registrado con ese correo.");
        }

        Rol rolBarbero = rolDao.findByNombreIgnoreCase("BARBERO");
        if (rolBarbero == null) {
            throw new IllegalStateException("No existe el rol BARBERO configurado en el sistema.");
        }

        datosUsuario.setRol(rolBarbero);
        datosUsuario.setActivo(true);
        usuarioDao.save(datosUsuario);

        Empleado empleado = new Empleado();
        empleado.setUsuario(datosUsuario);
        empleado.setEspecialidad(especialidad);
        empleado.setActivo(true);
        empleadoDao.save(empleado);
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}
